package com.nechay.jour.controllers;

import com.nechay.jour.utils.CjRoutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CjRoutes.TEST_ROUTE)
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public Mono<String> test() {
        log.info("Test");
        return Mono.just("Testing message");
    }
}
