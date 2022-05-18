package com.fse3.auction.cmd;

import com.fse3.auction.cmd.api.commands.*;
import com.fse3.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){
		commandDispatcher.registerHandler(AddProductCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(PlaceBidCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(UpdateBidCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeleteProductCommand.class, commandHandler::handle);
	}

}
