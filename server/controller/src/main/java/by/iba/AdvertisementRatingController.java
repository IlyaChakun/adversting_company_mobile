package by.iba;


import by.iba.common.dto.PageWrapper;
import by.iba.dto.req.advertisement.AdvertisementRatingReq;
import by.iba.dto.resp.advertisement.AdvertisementRatingResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/advertisements-rating")
@CrossOrigin(origins = "*")
public interface AdvertisementRatingController {

    @PostMapping()
    ResponseEntity<AdvertisementRatingResp> save(@RequestBody @Valid AdvertisementRatingReq req,
                                                 final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<AdvertisementRatingResp> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<AdvertisementRatingResp>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                 @RequestParam(defaultValue = "10", required = false) final Integer size);
}
