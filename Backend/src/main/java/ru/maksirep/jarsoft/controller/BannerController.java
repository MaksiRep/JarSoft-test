package ru.maksirep.jarsoft.controller;

import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maksirep.jarsoft.controller.requests.BannerRequest;
import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.service.BannersServiceImpl;
import ru.maksirep.jarsoft.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class BannerController {

    private final BannersServiceImpl bannersServiceImpl;

    private final RequestService requestService;

    public BannerController(BannersServiceImpl bannersServiceImpl, RequestService requestService) {
        this.bannersServiceImpl = bannersServiceImpl;
        this.requestService = requestService;
    }

    @Transient
    @GetMapping(value = "/bid")
    public ResponseEntity<String> getBanners(
            @RequestHeader(value = "User-Agent") String userAgent,
            @RequestParam(name = "cat") List<String> categories,
            HttpServletRequest request) throws IncorrectInputException, NotFoundException, EndBannersListException {
        String clientIp = requestService.getClientIp(request);
        String bannerText = bannersServiceImpl.getBid(categories, clientIp, userAgent);
        return new ResponseEntity<>(bannerText, HttpStatus.OK);
    }

    @Transient
    @PostMapping(value = "/banners")
    public ResponseEntity<Void> addBanner(@RequestBody BannerRequest bannerRequest) throws AlreadyExistException, IncorrectInputException {
        System.out.println(bannerRequest.getCategories());
        bannersServiceImpl.addBanner(bannerRequest.getName(),
                bannerRequest.getPrice(),
                bannerRequest.getCategories(),
                bannerRequest.getTextField());
        return ResponseEntity.ok().build();
    }


    @Transient
    @PutMapping(value = "/banners")
    public ResponseEntity<Void> putBanner(@RequestBody BannerRequest bannerRequest) throws AlreadyExistException, IncorrectInputException {
        bannersServiceImpl.updateBanner(bannerRequest.getId(),
                bannerRequest.getName(),
                bannerRequest.getPrice(),
                bannerRequest.getCategories(),
                bannerRequest.getTextField());
        return ResponseEntity.ok().build();
    }


    @Transient
    @DeleteMapping(value = "/banners/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable(name = "id") Integer id) throws EntityDeletedException {
        bannersServiceImpl.deleteBanner(id);
        return ResponseEntity.ok().build();
    }


    @Transient
    @GetMapping(value = "/banners")
    public ResponseEntity<List<Banner>> getBanners() throws NotFoundException {
        List<Banner> bannerList = bannersServiceImpl.getBanners();
        return new ResponseEntity<>(bannerList, HttpStatus.OK);
    }

}
