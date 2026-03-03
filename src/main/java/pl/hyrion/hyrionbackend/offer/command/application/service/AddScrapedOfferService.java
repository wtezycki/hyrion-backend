package pl.hyrion.hyrionbackend.offer.command.application.service;

import lombok.RequiredArgsConstructor;
import pl.hyrion.hyrionbackend.offer.command.application.port.in.AddScrapedOfferUseCase;
import pl.hyrion.hyrionbackend.offer.command.application.port.out.JobOfferRepositoryPort;
import pl.hyrion.hyrionbackend.offer.command.domain.JobOffer;
import pl.hyrion.hyrionbackend.offer.command.domain.Salary;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.ExperienceLevel;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.SourcePlatform;

@RequiredArgsConstructor
public class AddScrapedOfferService implements AddScrapedOfferUseCase {

    private final JobOfferRepositoryPort jobOfferRepositoryPort;

    @Override
    public void addOffer(AddOfferCommand command) {
        // Translating raw data to Value Objects and Enums
        Salary salary = createSalary(command);
        ExperienceLevel experienceLevel = ExperienceLevel.valueOf(command.experienceLevel().toUpperCase());
        SourcePlatform sourcePlatform = SourcePlatform.valueOf(command.sourcePlatform().toUpperCase());

        // Create aggregate:
        JobOffer jobOffer = JobOffer.builder()
                .title(command.title())
                .url(command.url())
                .location(command.location())
                .salary(salary)
                .skills(command.skills())
                .isRemote(command.isRemote())
                .createdAt(command.createdAt())
                .validTo(command.validTo())
                .originalId(command.originalId())
                .experienceLevel(experienceLevel)
                .sourcePlatform(sourcePlatform)
                .build();

        jobOfferRepositoryPort.save(jobOffer);
    }

    private Salary createSalary(AddOfferCommand command) {
        if (command.minSalary() != null && command.minSalary().equals(command.maxSalary())) {
            return Salary.exact(
                    command.minSalary(),
                    command.currency(),
                    command.paymentSchedule());
        }
        return Salary.range(
                command.minSalary(),
                command.maxSalary(),
                command.currency(),
                command.paymentSchedule()
        );
    }
}
