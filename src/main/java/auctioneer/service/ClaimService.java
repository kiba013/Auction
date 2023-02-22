package auctioneer.service;

import auctioneer.model.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> getAllClaim();

    Claim getClaimByID(Long id);


    void saveClaim(Claim claim, Long annID);



    void removeClaim(Long id);
}
