package com.fse3.auction.cmd.api.controllers;

import com.fse3.auction.cmd.api.commands.AddProductCommand;
import com.fse3.auction.cmd.api.dto.AddProductResponse;
import com.fse3.auction.common.dto.BaseResponse;
import com.fse3.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/e-auction/api/v1/seller/add-product")
public class AddProductController {
    private final Logger logger = Logger.getLogger(AddProductController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> addProduct(@RequestBody AddProductCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new AddProductResponse( "Product added successfully!", id), HttpStatus.CREATED);
        } catch(IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to add a product", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AddProductResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
