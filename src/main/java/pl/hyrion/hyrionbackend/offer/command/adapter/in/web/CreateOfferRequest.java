package pl.hyrion.hyrionbackend.offer.command.adapter.in.web;

import pl.hyrion.hyrionbackend.offer.command.application.port.in.AddScrapedOfferUseCase.AddOfferCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

// Represents JSON structure from Scrapers
public record CreateOfferRequest(
        String title,
        String url,
        String location,
        BigDecimal minSalary,
        BigDecimal maxSalary,
        String currency,
        String paymentSchedule,
        Set<String> skills,
        boolean isRemote,
        LocalDateTime createdAt,
        LocalDateTime validTo,
        String originalId,
        String experienceLevel,
        String sourcePlatform
) {
    // Maps JSON to Use Case
    public AddOfferCommand toCommand() {
        return new AddOfferCommand(
                this.title,
                this.url,
                this.location,
                this.minSalary,
                this.maxSalary,
                this.currency,
                this.paymentSchedule,
                this.skills,
                this.isRemote,
                this.createdAt,
                this.validTo,
                this.originalId,
                this.experienceLevel,
                this.sourcePlatform
        );
    }
}