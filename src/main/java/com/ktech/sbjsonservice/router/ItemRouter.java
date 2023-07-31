package com.ktech.sbjsonservice.router;

import com.ktech.sbjsonservice.handler.ItemHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ItemRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ItemHandler itemHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/items"), itemHandler::getItems)
                .andRoute(RequestPredicates.GET("/items/{id}"), itemHandler::getItemById);
    }
}
