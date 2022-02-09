package ru.maksirep.jarsoft.service;

import org.springframework.stereotype.Service;
import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.model.Category;
import ru.maksirep.jarsoft.repository.BannersRepository;
import ru.maksirep.jarsoft.repository.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    final BannersRepository bannersRepository;

    final CategoriesRepository categoriesRepository;

    public CategoryServiceImpl(BannersRepository bannersRepository, CategoriesRepository categoriesRepository) {
        this.bannersRepository = bannersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public void addCategory(String name, String requestId) throws AlreadyExistException, IncorrectInputException {
        if (name.equals("") || requestId.equals(""))
            throw new IncorrectInputException("Incorrect input");

        if (categoriesRepository.findByRequestId(requestId) != null) {
            throw new AlreadyExistException("Category with this requestId already exist in Database");
        }

        if (categoriesRepository.findByName(name) != null) {
            throw new AlreadyExistException("Category with this name already exist in Database");
        }

        Category deletedCategory = categoriesRepository.findByNameAndRequestId(name, requestId);
        if (deletedCategory != null && deletedCategory.getDeleted()) {
            Category category = categoriesRepository.findById(deletedCategory.getId());
            category.setName(name);
            category.setRequestId(requestId);
            categoriesRepository.saveAndFlush(category);
        } else if (deletedCategory == null) {
            Category category = new Category(name, requestId, false);
            categoriesRepository.save(category);
        }
    }

    public void updateCategory(int id, String name, String requestId) throws EntityDeletedException, AlreadyExistException, IncorrectInputException {
        Category category = categoriesRepository.findById(id);

        if (name.equals("") || requestId.equals(""))
            throw new IncorrectInputException("Incorrect input");

        if (category.getDeleted())
            throw (new EntityDeletedException("Category deleted in the database"));

        if (categoriesRepository.findByName(name) != null && id!= categoriesRepository.findByName(name).getId())
            throw new AlreadyExistException("Category with this name already exist in the database");

        System.out.println(requestId);
        System.out.println(categoriesRepository.findByRequestId(requestId).getRequestId());
        if (categoriesRepository.findByRequestId(requestId) != null && id!= categoriesRepository.findByRequestId(requestId).getId() )
            throw new AlreadyExistException("Category with this requestId already exist in the database");

        category.setName(name);
        category.setRequestId(requestId);
        categoriesRepository.saveAndFlush(category);

    }

    public void deleteCategory(int id) throws NotFoundException, EntityDeletedException, CategoryDeleteException {
        Category category = categoriesRepository.findById(id);
        List<Banner> bannerList = bannersRepository.findBannersByDeletedFalseAndCategories(category);

        if (category == null)
            throw (new NotFoundException("Category not found in DataBase"));

        if (category.getDeleted())
            throw (new EntityDeletedException("Category already deleted in the database"));

        if (bannerList == null || bannerList.isEmpty()) {
            category.setDeleted(true);
            categoriesRepository.saveAndFlush(category);
        } else {
            List<Integer> banId = new ArrayList<>();
            for (Banner b : bannerList) {
                banId.add(b.getId());
            }
            throw (new CategoryDeleteException("The category has banners " + banId));
        }
    }

    public List<Category> getCategories() {
        return categoriesRepository.findCategoriesByDeletedFalse();
    }
}