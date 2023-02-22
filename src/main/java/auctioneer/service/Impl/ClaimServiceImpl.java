package auctioneer.service.Impl;

import auctioneer.model.Announcement;
import auctioneer.model.Claim;
import auctioneer.repository.AnnouncementRepository;
import auctioneer.repository.ClaimRepository;
import auctioneer.service.ClaimService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {



    private final ClaimRepository claimRepository;


    private final AnnouncementRepository announcementRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository, AnnouncementRepository announcementRepository) {
        this.claimRepository = claimRepository;
        this.announcementRepository = announcementRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Claim> getAllClaim() {
        return (List<Claim>) claimRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public Claim getClaimByID(Long id) {
        return claimRepository.findClaimById(id);
    }

    public Claim findClaimPriceByAnnID(Long id) {
        List<Claim> claimList = claimRepository.findByAnnouncement_Id(id);
        Long price = 0L;
        Claim claim1 = new Claim();
        for(Claim claim : claimList) {
            Long minPrice = claim.getPrice();
            if(minPrice > price) {
                price = minPrice;
                claim1 = claim;
            }

        }
        return claim1;
    }

    @Override
    public void saveClaim(Claim claim, Long annID) {

        Claim curClaim = findClaimPriceByAnnID(annID);

        Long priced = curClaim.getPrice();
        Long finishPrice = claim.getPrice();

        Announcement announcement = announcementRepository.findById(annID).get();

        Long startPrice = announcement.getMinPrice();

        claim.setAnnouncement(announcement);


        LocalTime time = curClaim.getPublishedAt().toLocalTime();
        LocalTime time2 = claim.getPublishedAt().toLocalTime();



        if(startPrice < finishPrice && finishPrice > priced) {
            if (time2.isAfter(time))
            claimRepository.save(claim);
        }
    }

    @Override
    public void removeClaim(Long id) {
        claimRepository.deleteById(id);
    }
}
