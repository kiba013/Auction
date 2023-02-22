package auctioneer.repository;

import auctioneer.model.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {

    Announcement findAnnouncementByNameProduct(String nameProduct);

}
