package com.fse3.auction.query;

import com.fse3.auction.query.api.queries.FindAllBidsByProductId;
import com.fse3.auction.query.api.queries.FindProductInfo;
import com.fse3.auction.query.api.queries.QueryHandler;
import com.fse3.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;
	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){
		queryDispatcher.registerHandler(FindAllBidsByProductId.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindProductInfo.class, queryHandler::handle);
	}

}
