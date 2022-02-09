package ru.maksirep.jarsoft.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.model.Category;
import ru.maksirep.jarsoft.model.Logs;
import ru.maksirep.jarsoft.repository.LogsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class LogServiceImpl implements LogService {

    final LogsRepository logsRepository;

    private final int DELAY_FOR_START = 86400000; // DELETE LOGS EVERY DAY
    private final int TIME_FOR_CLEAN_LOGS = 86400000;

    public LogServiceImpl(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    @Override
    public List<Banner> findBannersInLogs(String userAgent, String ip) {
        List<Logs> logsList = logsRepository.findAllByUserAgentAndIpAddress(userAgent, ip);
        List<Banner> bannerList = new ArrayList<>();
        for (Logs b : logsList) {
            bannerList.add(b.getBanner());
        }
        return bannerList;
    }

    @Override
    public void saveLogs(String userAgent, String ip, String reason, Banner banner, List<Category> categoryList) {
        logsRepository.save(new Logs(userAgent, ip, banner.getPrice(), reason, banner, categoryList));
    }

    @Override
    @Scheduled(fixedDelay = TIME_FOR_CLEAN_LOGS, initialDelay = DELAY_FOR_START)
    public void deleteAll() {
        System.out.println("hi");
    }
}
