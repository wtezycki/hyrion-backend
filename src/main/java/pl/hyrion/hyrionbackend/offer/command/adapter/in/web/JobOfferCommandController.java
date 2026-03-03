package pl.hyrion.hyrionbackend.offer.command.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hyrion.hyrionbackend.offer.command.application.port.in.AddScrapedOfferUseCase;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class JobOfferCommandController {

    private final AddScrapedOfferUseCase addScrapedOfferUseCase;

    @PostMapping
    public ResponseEntity<Void> addOffer(@RequestBody CreateOfferRequest request) {
        addScrapedOfferUseCase.addOffer(request.toCommand());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
