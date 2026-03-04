package pl.hyrion.hyrionbackend.offer.command.application.port.out;


import pl.hyrion.hyrionbackend.offer.command.domain.JobOffer;

public interface JobOfferRepositoryPort {
    void save(JobOffer jobOffer);
}
