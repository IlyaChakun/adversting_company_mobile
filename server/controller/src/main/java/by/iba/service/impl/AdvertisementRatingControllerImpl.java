package by.iba.service.impl;


import by.iba.AdvertisementRatingController;
import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.dto.req.advertisement.AdvertisementRatingReq;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.resp.advertisement.AdvertisementRatingResp;
import by.iba.entity.user.AdvertisementRating;
import by.iba.service.AdvertisementRatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Slf4j
public class AdvertisementRatingControllerImpl implements AdvertisementRatingController {

    private final AdvertisementRatingService advertisementService;

    @Override
    public ResponseEntity<AdvertisementRatingResp> save(AdvertisementRatingReq req,
                                                        BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final AdvertisementRatingResp saved = advertisementService.save(req);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);
    }

    @Override
    public ResponseEntity<AdvertisementRatingResp> findById(Long id) {

        final AdvertisementRatingResp response = advertisementService.findById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<AdvertisementRatingResp>> findAll(Integer page, Integer size) {
        final PageWrapper<AdvertisementRatingResp> pageWrapper = advertisementService.findAll(page, size);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

}
