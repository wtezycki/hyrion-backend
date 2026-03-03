package pl.hyrion.hyrionbackend.offer.command.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hyrion.hyrionbackend.offer.command.application.port.in.AddScrapedOfferUseCase;
import pl.hyrion.hyrionbackend.offer.command.application.port.out.JobOfferRepositoryPort;
import pl.hyrion.hyrionbackend.offer.command.application.service.AddScrapedOfferService;

@Configuration
public class OfferCommandConfiguration {

    @Bean
    public AddScrapedOfferUseCase addScrapedOfferUseCase(JobOfferRepositoryPort repositoryPort){
        return new AddScrapedOfferService(repositoryPort);
    }

}
