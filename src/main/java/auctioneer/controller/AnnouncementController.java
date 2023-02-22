package auctioneer.controller;

import auctioneer.model.Announcement;
import auctioneer.model.User;
import auctioneer.service.AnnouncementService;
import auctioneer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@Api(tags = {"Auction Board Controller"})
public class AnnouncementController {


    private final AnnouncementService announcementService;

    private final UserService userService;

    public AnnouncementController(AnnouncementService announcementService, UserService userService) {
        this.announcementService = announcementService;
        this.userService = userService;
    }


    @GetMapping("/announcements")
    @ApiOperation("Get all Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public List<Announcement> getAllAnnouncement() {
        return announcementService.getAllOrder();
    }


    @PostMapping("/saveOrder")
    @ApiOperation("Creating Announcement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное сохранение"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity createOrder(@RequestBody Announcement announcement,
                                      Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        announcement.setUser(user);
        announcementService.saveAnnouncement(announcement);
        return ResponseEntity.ok(announcement);
    }
}
