package by.iba;


import by.iba.common.dto.PageWrapper;
import by.iba.dto.AdvertisementReqParams;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.req.advertisement.AdvertisementUpdateReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/advertisements")
@CrossOrigin(origins = "*")
public interface AdvertisementController {

    @PostMapping()
    ResponseEntity<AdvertisementResp> save(@RequestBody @Valid AdvertisementReq req,
                                           final BindingResult bindingResult);

    @PostMapping("/update-status")
    ResponseEntity<AdvertisementResp> updateStatus(@RequestBody @Valid AdvertisementUpdateReq req,
                                           final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<AdvertisementResp> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<AdvertisementResp>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                           @RequestParam(defaultValue = "10", required = false) final Integer size,
                                                           AdvertisementReqParams advertisementReqParams);
}
