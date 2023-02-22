package auctioneer.controller;


import auctioneer.model.User;
import auctioneer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"User Controller"})
@RequestMapping("/api/account")
public class AuctionMainController {



    private final UserService userService;

    public AuctionMainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/main")
    public String getTest() {
        return "hello";
    }

    @GetMapping("/users")
    @ApiOperation("Get All User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа AccountDTO"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public List<User> getUsers() {
        List<User> user = userService.getAllUser();
        return user;
    }


    @PostMapping("/usering")
    @ApiOperation("Saving new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание AccountDTO"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }


}

