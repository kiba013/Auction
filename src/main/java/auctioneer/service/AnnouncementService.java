package auctioneer.service;

import auctioneer.model.Announcement;

import java.util.List;

public interface AnnouncementService {

    List<Announcement> getAllOrder();

    Announcement getAnnouncementByID(Long id);

    void saveAnnouncement(Announcement announcement);

    Announcement editAnnouncement(Long id, Announcement announcement);

    void removeAnnouncement(Long id);

}
