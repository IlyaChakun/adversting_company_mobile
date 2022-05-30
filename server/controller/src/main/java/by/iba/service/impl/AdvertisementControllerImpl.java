package by.iba.service.impl;


import by.iba.AdvertisementController;
import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.dto.AdvertisementReqParams;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.req.advertisement.AdvertisementUpdateReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;
import by.iba.service.AdvertisementService;
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
public class AdvertisementControllerImpl implements AdvertisementController {

    private final AdvertisementService advertisementService;

    @Override
    public ResponseEntity<AdvertisementResp> save(AdvertisementReq transportationRequestDTO,
                                                  BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final AdvertisementResp saved = advertisementService.save(transportationRequestDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);
    }

    @Override
    public ResponseEntity<AdvertisementResp> updateStatus(AdvertisementUpdateReq req, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final AdvertisementResp saved = advertisementService.udpate(req);

        return ResponseEntity
                .ok(saved);
    }

    @Override
    public ResponseEntity<AdvertisementResp> findById(Long id) {

        final AdvertisementResp response = advertisementService.findById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<AdvertisementResp>> findAll(Integer page, Integer size, AdvertisementReqParams advertisementReqParams) {
        final PageWrapper<AdvertisementResp> pageWrapper = advertisementService.findAll(page, size, advertisementReqParams);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<AdvertisementResp>> findAllForAdmin(Integer page, Integer size, AdvertisementReqParams advertisementReqParams) {
        final PageWrapper<AdvertisementResp> pageWrapper = advertisementService.findAllForAdmin(page, size, advertisementReqParams);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

}
