package com.fse3.auction.cmd.api.controllers;

import com.fse3.auction.cmd.api.commands.DeleteProductCommand;
import com.fse3.auction.common.dto.BaseResponse;
import com.fse3.cqrs.core.exceptions.AggregateNotFoundException;
import com.fse3.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/deleteProduct")
public class DeleteProductController {
    private final Logger logger = Logger.getLogger(DeleteProductController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable(value = "id") String id){
        try{
            commandDispatcher.send(new DeleteProductCommand(id));
            return new ResponseEntity<>(new BaseResponse("Delete Product request successfully completed!"), HttpStatus.OK);
        }catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to delete product with id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
