package com.ktech.sbjsonservice.handler;

import com.ktech.sbjsonservice.model.Item;
import com.ktech.sbjsonservice.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ItemHandler {
    @Autowired
    private ItemService itemService;
    private Logger logger = LoggerFactory.getLogger(ItemHandler.class);
    public Mono<ServerResponse> getItems(ServerRequest request) {
        logger.info("getItems");
        return ServerResponse.ok().body(itemService.getItems(), Item.class);
    }

    public Mono<ServerResponse> getItemById(ServerRequest request) {
        int itemId = Integer.parseInt(request.pathVariable("id"));
        logger.info("getItemById - " + itemId);
        return ServerResponse.ok().body(itemService.getItemById(itemId), Item.class);
    }
}
