package ru.maksirep.jarsoft.service;

import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.model.Category;

import java.util.List;

public interface LogService {

    List<Banner> findBannersInLogs (String userAgent, String ip);

    void saveLogs (String userAgent, String ip, String reason, Banner banner, List<Category> categoryList);

}
