package auctioneer.service.Impl;

import auctioneer.model.Announcement;
import auctioneer.repository.AnnouncementRepository;
import auctioneer.service.AnnouncementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AnnouncementImpl implements AnnouncementService {


    private final AnnouncementRepository announcementRepository;

    public AnnouncementImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Announcement> getAllOrder() {
        return (List<Announcement>) announcementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Announcement getAnnouncementByID(Long id) {
        return announcementRepository.findById(id).get();
    }

    @Override
    public void saveAnnouncement(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        announcementRepository.save(announcement);
    }

    @Override
    public Announcement editAnnouncement(Long id, Announcement announcement) {
        Announcement announcement1 = getAnnouncementByID(id);
        announcement1.setNameProduct(announcement.getNameProduct());
        announcement1.setDescription(announcement.getDescription());
        announcement1.setActive(announcement.getActive());
        announcement1.setImageProduct(announcement.getImageProduct());
        return announcement1;
    }

    @Override
    public void removeAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}
