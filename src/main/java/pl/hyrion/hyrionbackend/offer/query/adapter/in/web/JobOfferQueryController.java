package pl.hyrion.hyrionbackend.offer.query.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hyrion.hyrionbackend.offer.query.application.service.JobOfferQueryService;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class JobOfferQueryController {

    private final JobOfferQueryService queryService;

    @GetMapping
    public ResponseEntity<Page<JobOfferView>> getOffers(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String experienceLevel,
            @RequestParam(required = false) String sourcePlatform,
            @RequestParam(required = false) Boolean isRemote,
            @RequestParam(required = false) BigDecimal minSalary,
            @RequestParam(required = false) String skill,
            Pageable pageable) {

        Page<JobOfferView> offers = queryService.getAllJobOffers(
                location, experienceLevel, sourcePlatform, isRemote, minSalary, skill, pageable);

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferView> getOfferById(@PathVariable UUID id) {
        JobOfferView offer = queryService.getJobOfferById(id);
        return ResponseEntity.ok(offer);
    }
}
