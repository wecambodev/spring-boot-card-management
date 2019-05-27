package com.mbanq.banksystem.controller;
import com.mbanq.banksystem.dto.CardActivateRequest;
import com.mbanq.banksystem.dto.CardDailyLimitRequest;
import com.mbanq.banksystem.dto.CardDeactivateRequest;
import com.mbanq.banksystem.dto.CardDetailRequest;
import com.mbanq.banksystem.exception.ValidationException;
import com.mbanq.banksystem.model.Card;
import com.mbanq.banksystem.security.JwtUser;
import com.mbanq.banksystem.security.service.JwtUserDetailsService;
import com.mbanq.banksystem.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/api/card")
@Api(tags = "Card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     *  Fetch a list of cards for a specific user by token
     *
     *
     * **/

    @GetMapping(value = "/list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation, Find list card success."),
            @ApiResponse(code = 401, message = "Operation failed, token not provide"),
            @ApiResponse(code = 422, message = "Operation failed"),
            @ApiResponse(code = 500, message = "Something went wrong.")
    })

    public ResponseEntity<?> findAllByAccountId(HttpServletRequest request){
        JwtUser jwtUser = userDetailsService.getUserFromHeaderToken(request);

        if(jwtUser==null) {
            HashMap res = new HashMap();
            res.put("status_code", 401);
            res.put("message", "Operation failed, Invalid token ");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        List<Card> cards = cardService.findAllActiveByConsumerId(jwtUser.getId());
        if(cards !=null) {

            HashMap res = new HashMap();
            res.put("status_code", 200);
            res.put("message", "Operation, Find list card success.");
            res.put("data", cards);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        HashMap res = new HashMap();
        res.put("status_code", 422);
        res.put("message", "Operation failed");

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }


    /**
     *
     * Fetch the card details for a specific card. The details should include the cardholder name, address, daily limit, balance, card number, expiration date, and status
     *
     *
     * **/
    @ApiResponses(value = {//
            @ApiResponse(code = 200, message = "Operation, Detail of  card success."),
            @ApiResponse(code = 401, message = "Operation failed, token not provide"),
            @ApiResponse(code = 422, message = "Operation failed"),
            @ApiResponse(code = 500, message = "Something went wrong.")
    })
    @GetMapping(value = "/detail")
    public ResponseEntity<?> cardDetail(@Valid @RequestBody CardDetailRequest request, BindingResult result){

        if(result.hasErrors()) {
            throw  new ValidationException("Operation card detail invalid", result);
        }

        Card inputCard = modelMapper.map(request, Card.class);
        Card existCard = cardService.findById(inputCard.getId());
        if(existCard !=null) {
            HashMap res = new HashMap();
            res.put("data", existCard);
            res.put("message", "Operation, Detail of  card success.");
            res.put("status_code", 200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        HashMap res = new HashMap();
        res.put("status_code", 422);
        res.put("message", "Something went wrong.");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }



    /***
     *
     *
     *  Activate the card
     *
     *
     *
     * **/
    @PutMapping(value = "/activate")
    @ApiResponses(value = {//
            @ApiResponse(code = 200, message = "Operation, Activate  success"),
            @ApiResponse(code = 400, message = "Input invalid, Validation"),
            @ApiResponse(code = 422, message = "Input invalid, Can' activate because int already activate "), //
            @ApiResponse(code = 500, message = "Something went wrong.")
    })
    public ResponseEntity<?> activate(@Valid @RequestBody CardActivateRequest request, BindingResult result){
        // invalid input
        if(result.hasErrors()) {
            throw  new ValidationException("Input card id not correct", result);
        }

        Card inputCard = modelMapper.map(request, Card.class);
        Card existCard = cardService.findById(inputCard.getId());
        if(existCard !=null) {

            // Check if card already deactivate
            if(existCard.getStatus().equals("1")) {
                HashMap res = new HashMap();
                res.put("status_code", 422);
                res.put("message", "Input invalid, Can' activate because int already activate");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            // process deactivate service
            existCard.setStatus(inputCard.getStatus());
            if(cardService.activate(existCard.getId())) {
                HashMap res = new HashMap();
                res.put("data", existCard);
                res.put("message", "Operation, Activate  success");
                res.put("status_code", 200);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }

        HashMap res = new HashMap();
        res.put("status_code", 500);
        res.put("message", "Something went wrong.");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }




    /***
     *
     *
     *  Deactivate the card
     *
     *
     *
     * **/
    @PutMapping(value = "/deactivate")
    @ApiResponses(value = {//
            @ApiResponse(code = 200, message = "Operation, Deactivate  success"), //
            @ApiResponse(code = 400, message = "Input invalid, Validation"), //
            @ApiResponse(code = 422, message = "Input invalid, Can' deactivate because int already deactivate "), //
            @ApiResponse(code = 500, message = "Something went wrong.")
    })
    public ResponseEntity<?> deactivate(@Valid @RequestBody CardDeactivateRequest request, BindingResult result){
        // invalid input
        if(result.hasErrors()) {
            throw  new ValidationException("Input card id not correct", result);
        }

        Card inputCard = modelMapper.map(request, Card.class);
        Card existCard = cardService.findById(inputCard.getId());
        if(existCard !=null) {

            // Check if card already deactivate
            if(existCard.getStatus().equals("0")) {
                HashMap res = new HashMap();
                res.put("status_code", 422);
                res.put("message", "Input invalid, Can' deactivate because int already deactivate");
                  return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            // process deactivate service
            existCard.setStatus(inputCard.getStatus());
            if(cardService.deactivate(existCard.getId())) {
                HashMap res = new HashMap();
                res.put("data", existCard);
                res.put("message", "Operation, Deactivate  success");
                res.put("status_code", 200);
                return new ResponseEntity<>(res, HttpStatus.OK);

            }


        }

        HashMap res = new HashMap();
        res.put("status_code", 500);
        res.put("message", "Something went wrong.");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }





    /***
     *
     *
     *  Change daily limit
     *
     *
     *
     * **/
    @PutMapping(value = "/daily-limit")
    @ApiResponses(value = {//
            @ApiResponse(code = 200, message = "Operation success"), //
            @ApiResponse(code = 400, message = "Input invalid, Validation"), //
            @ApiResponse(code = 422, message = "Input invalid, Business maximum"), //
            @ApiResponse(code = 500, message = "Something went wrong.")
    })
    public ResponseEntity<?> dailyLimit(@Valid @RequestBody CardDailyLimitRequest request,
                                        BindingResult result
                                        ){
        if(result.hasErrors()) {
            throw  new ValidationException("Incorrect DailyLimit", result);
        }

        // Map request card and card model
        Card inputCard =  modelMapper.map(request, Card.class);

        // find existing card
        Card existCard = cardService.findActiveById(inputCard.getId());
        if(existCard !=null) {
            // set new daily limit
            existCard.setDailyLimit(inputCard.getDailyLimit());
            if(cardService.changeDailyLimit(existCard)) {
                HashMap res = new HashMap();
                res.put("data", existCard);
                res.put("message", "Change dailyLimit Successful.");
                res.put("status_code", 200);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }

        HashMap res = new HashMap();
        res.put("status_code", 422);
        res.put("message", "Something went wrong.");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }


}
