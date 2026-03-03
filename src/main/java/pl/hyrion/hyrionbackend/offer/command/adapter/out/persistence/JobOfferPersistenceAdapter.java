package pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.hyrion.hyrionbackend.offer.command.application.port.out.JobOfferRepositoryPort;
import pl.hyrion.hyrionbackend.offer.command.domain.JobOffer;
import pl.hyrion.hyrionbackend.offer.command.domain.Salary;

@Component
@RequiredArgsConstructor
public class JobOfferPersistenceAdapter implements JobOfferRepositoryPort {

    private final JobOfferJpaRepository repository;

    @Override
    public void save(JobOffer jobOffer) {
        // Translate Domain Aggregate to Hibernate Entity
        JobOfferEntity entity = toEntity(jobOffer);

        repository.save(entity);
    }

    // Custom Aggregate -> Entity Mapper
    private JobOfferEntity toEntity(JobOffer jo) {
        Salary salary = jo.getSalary();

        return JobOfferEntity.builder()
                .id(jo.getId().value())
                .title(jo.getTitle())
                .url(jo.getUrl())
                .location(jo.getLocation())

                // salary + (validation)
                .minSalary(salary != null ? salary.min() : null)
                .maxSalary(salary != null ? salary.max() : null)
                .currency(salary != null ? salary.currency() : null)
                .paymentSchedule(salary != null ? salary.paymentSchedule() : null)

                .isRemote(jo.isRemote())
                .createdAt(jo.getCreatedAt())
                .validTo(jo.getValidTo())
                .originalId(jo.getOriginalId())

                // skills
                .skills(jo.getSkills())

                // Translating Enums to safe strings
                .experienceLevel(jo.getExperienceLevel() != null ? jo.getExperienceLevel().name() : null)
                .sourcePlatform(jo.getSourcePlatform() != null ? jo.getSourcePlatform().name() : null)
                .status(jo.getStatus() != null ? jo.getStatus().name() : null)

                .build();

    }

}
