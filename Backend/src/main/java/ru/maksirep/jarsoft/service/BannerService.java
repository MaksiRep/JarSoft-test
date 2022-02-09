package ru.maksirep.jarsoft.service;

import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Banner;

import java.util.List;

public interface BannerService {

    void addBanner(String name, double price, List<String> requestId, String textField)
            throws AlreadyExistException, IncorrectInputException;

    void updateBanner(int id, String name, double price, List<String> requestId, String textField)
            throws AlreadyExistException, IncorrectInputException;

    void deleteBanner(int id) throws EntityDeletedException;

    List<Banner> getBanners() throws NotFoundException;

    String getBid(List<String> categories, String ip, String userAgent)
            throws IncorrectInputException, NotFoundException, EndBannersListException;

}
