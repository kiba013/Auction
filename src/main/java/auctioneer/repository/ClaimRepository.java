package auctioneer.repository;

import auctioneer.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Claim findClaimById(Long id);

    List<Claim> findByAnnouncement_Id(Long id);

}
