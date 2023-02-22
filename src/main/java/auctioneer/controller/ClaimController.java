package auctioneer.controller;

import auctioneer.model.Claim;
import auctioneer.model.User;
import auctioneer.service.ClaimService;
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
@RequestMapping("api/claim")
@Api(tags = {"Claim Controller"})
public class ClaimController {


    private final ClaimService claimService;


    private final UserService userService;

    public ClaimController(ClaimService claimService, UserService userService) {
        this.claimService = claimService;
        this.userService = userService;
    }

    @GetMapping("/claims")
    @ApiOperation("Get all Claims")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public List<Claim> getAllClaim() {
        return claimService.getAllClaim();
    }


    @PostMapping("/saveClaim")
    @ApiOperation("Save new Claim")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное сохранение"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity saveClaim(@RequestBody Claim claim, Long annID,
                                    Principal principal) {

        User user = userService.getUserByEmail(principal.getName());

        claim.setUser(user);

        claimService.saveClaim(claim, annID);
        return ResponseEntity.ok(claim);
    }
}
