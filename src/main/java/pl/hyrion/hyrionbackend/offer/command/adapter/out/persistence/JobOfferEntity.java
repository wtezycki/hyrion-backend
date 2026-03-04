package pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence;

// Database entity for Job Offer

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "job_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferEntity {

    @Id
    private UUID id;

    private String title;
    private String url;
    private String location;

    // Unpacking salary
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String currency;
    private String paymentSchedule;

    // Relationship 1:N for list of skills
    // job_offer_skills
    // LAZY fetch type:
    // in application.properties configurated Hibernate fetching for skills
    @ElementCollection
    @CollectionTable(
            name = "job_offer_skills",
            joinColumns = @JoinColumn(name = "job_offer_id"))
    @Column(name = "skill")
    private Set<String> skills;

    private boolean isRemote;
    private LocalDateTime createdAt;
    private LocalDateTime validTo;
    private String originalId;

    @Column(name = "experience_level")
    private String experienceLevel;

    @Column(name = "source_platform")
    private String sourcePlatform;

    @Column(name = "status")
    private String status;

}
