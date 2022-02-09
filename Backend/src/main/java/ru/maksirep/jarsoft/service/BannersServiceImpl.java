package ru.maksirep.jarsoft.service;

import org.springframework.stereotype.Service;
import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.model.Category;
import ru.maksirep.jarsoft.repository.BannersRepository;
import ru.maksirep.jarsoft.repository.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class BannersServiceImpl implements BannerService {

    final BannersRepository bannersRepository;

    final CategoriesRepository categoriesRepository;

    final LogServiceImpl logServiceImpl;

    public BannersServiceImpl(BannersRepository bannersRepository, CategoriesRepository categoriesRepository, LogServiceImpl logServiceImpl) {
        this.bannersRepository = bannersRepository;
        this.categoriesRepository = categoriesRepository;
        this.logServiceImpl = logServiceImpl;
    }

    @Override
    public void addBanner(String name, double price, List<String> requestId, String textField)
            throws AlreadyExistException, IncorrectInputException {

        if (name.isEmpty() || textField.isEmpty() || requestId.isEmpty() || price == 0.0)
            throw new IncorrectInputException("Incorrect input");

        List<Category> categoryList = categoriesRepository.findAllByRequestIdIn(requestId);

        for (Category c : categoryList) {
            if (c.getName().equals("") || c.getRequestId().equals(""))
                throw new IncorrectInputException("Incorrect input");
        }

        if (categoryList.size() != requestId.size())
            throw (new IncorrectInputException("Incorrect entry of the list of categories\n"));


        for (Category c :
                categoryList) {
            if (!categoriesRepository.findCategoriesByDeletedFalse().contains(c)) {
                throw (new IncorrectInputException("One or more categories are deleted"));
            }
        }

        Banner deletedBanner = bannersRepository.findBannerByName(name);
        if (deletedBanner != null && deletedBanner.getDeleted()) {
            Banner banner = new Banner(name, price, textField, categoryList, false);
            banner.setId(deletedBanner.getId());
            bannersRepository.saveAndFlush(banner);
        } else if (deletedBanner == null) {
            Banner banner = new Banner(name, price, textField, categoryList, false);
            bannersRepository.save(banner);
        } else throw (new AlreadyExistException("The banner is already exist in the database"));


    }

    @Override
    public void updateBanner(int id, String name, double price, List<String> requestId, String textField)
            throws AlreadyExistException, IncorrectInputException {

        if (name.isEmpty() || textField.isEmpty() || requestId.isEmpty() || price == 0.0)
            throw new IncorrectInputException("Incorrect input");

        if (bannersRepository.findBannerByName(name) != null) {
            if (bannersRepository.findBannerByName(name).getId() != id)
                throw new AlreadyExistException("Banner with this name already exist in the database");
        }
        List<Category> categoryList = categoriesRepository.findAllByRequestIdIn(requestId);
        Banner banner = new Banner(name, price, textField, categoryList, false);
        banner.setId(id);
        bannersRepository.saveAndFlush(banner);

    }

    @Override
    public void deleteBanner(int id) throws EntityDeletedException {
        if (bannersRepository.findById(id).getDeleted())
            throw new EntityDeletedException("This banner already deleted");

        Banner banner = bannersRepository.findById(id);
        banner.setDeleted(true);
        bannersRepository.saveAndFlush(banner);
    }

    @Override
    public List<Banner> getBanners() throws NotFoundException {
        if (bannersRepository.findBannersByDeletedFalse() == null || bannersRepository.findBannersByDeletedFalse().isEmpty())
            throw new NotFoundException("Banners list is empty");
        return bannersRepository.findBannersByDeletedFalse();
    }

    @Override
    public String getBid(List<String> categories, String ip, String userAgent)
            throws IncorrectInputException, NotFoundException, EndBannersListException {

        List<Category> categoryList = categoriesRepository.findAllByRequestIdIn(categories);

        if (categoryList.size() != categories.size())
            throw (new IncorrectInputException("Incorrect entry of the list of categories\n"));

        Set<Banner> bannerSet = bannersRepository.findBannersByDeletedFalseAndCategoriesIn(categoryList);

        if (bannerSet == null || bannerSet.isEmpty())
            throw (new NotFoundException("Banner list is empty"));

        List<Banner> bannerList = new ArrayList<>(bannerSet);

        List<Banner> bannersInLogs = logServiceImpl.findBannersInLogs(userAgent, ip);


        double maxPrice = 0;
        int id = 0;

        if (bannersInLogs == null || bannersInLogs.isEmpty()) {
            for (Banner b : bannerList) {
                if (b.getPrice() > maxPrice && b.getCategories().size() == categoryList.size()) {
                    maxPrice = b.getPrice();
                    id = b.getId();
                }
            }
        } else {
            for (Banner b : bannerList) {
                if (!bannersInLogs.contains(b) && b.getCategories().size() == categoryList.size()) {
                    if (b.getPrice() > maxPrice) {
                        maxPrice = b.getPrice();
                        id = b.getId();
                    }
                }
            }
        }

        if (id == 0) {
            logServiceImpl.saveLogs(userAgent, ip, "All banners with these categories have been viewed", bannerList.get(0), categoryList);
            throw (new EndBannersListException("All banners with these categories have been viewed"));
        }

        Banner out = bannersRepository.getById(id);
        logServiceImpl.saveLogs(userAgent, ip, "", out, categoryList);
        return out.getTextField();
    }

}