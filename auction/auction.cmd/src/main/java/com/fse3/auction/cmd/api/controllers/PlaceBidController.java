package com.fse3.auction.cmd.api.controllers;

import com.fse3.auction.cmd.api.commands.PlaceBidCommand;
import com.fse3.auction.cmd.api.dto.AddProductResponse;
import com.fse3.auction.common.dto.BaseResponse;
import com.fse3.cqrs.core.exceptions.AggregateNotFoundException;
import com.fse3.cqrs.core.infrastructure.CommandDispatcher;
import com.fse3.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/buyer/place-bid")
public class PlaceBidController {
    private final Logger logger = Logger.getLogger(PlaceBidController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> placeBid(@RequestBody PlaceBidCommand command){
        var id = UUID.randomUUID().toString();
        try{
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new AddProductResponse("Bid has successfully been placed", id), HttpStatus.CREATED);
        } catch(IllegalStateException | AggregateNotFoundException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to place a bid", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
