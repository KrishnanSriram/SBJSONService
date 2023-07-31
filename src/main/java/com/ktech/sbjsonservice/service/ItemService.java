package com.ktech.sbjsonservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktech.sbjsonservice.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ItemService {
    private final List<Item> items;
    private Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService() throws IOException {
        logger.info("ItemService constructor");
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource("data.json");
        try(InputStream inputStream = classPathResource.getInputStream()) {
            items = objectMapper.readValue(inputStream, new TypeReference<List<Item>>() {});
            logger.info("Items loaded - " + items.size());
        }
    }

    public Flux<Item> getItems() {
        logger.info("getItems");
        logger.info(items.toString());
        return Flux.fromIterable(items);
    }

    public Mono<Item> getItemById(int id) {
        logger.info("getItemById - " + id);
        Flux<Item> fitems = Flux.fromIterable(items);
        return fitems.filter(item -> item.getId() == id)
                .singleOrEmpty();
    }
}
