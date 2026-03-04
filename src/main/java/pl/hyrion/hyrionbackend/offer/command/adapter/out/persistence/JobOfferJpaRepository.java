package pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;

import java.math.BigDecimal;
import java.util.UUID;


public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, UUID> {

    @Query("SELECT o FROM JobOfferEntity o WHERE " +
            "(:location IS NULL OR LOWER(o.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:experienceLevel IS NULL OR o.experienceLevel = :experienceLevel) AND " +
            "(:sourcePlatform IS NULL OR o.sourcePlatform = :sourcePlatform) AND " +
            "(:isRemote IS NULL OR o.isRemote = :isRemote) AND " +
            "(:minSalary IS NULL OR o.maxSalary >= :minSalary) AND " +
            "(:skill IS NULL OR :skill MEMBER OF o.skills)") // Magia do przeszukiwania list!
    Page<JobOfferEntity> findByFilters(
            @Param("location") String location,
            @Param("experienceLevel") String experienceLevel,
            @Param("sourcePlatform") String sourcePlatform,
            @Param("isRemote") Boolean isRemote,
            @Param("minSalary") BigDecimal minSalary,
            @Param("skill") String skill,
            Pageable pageable);


}
