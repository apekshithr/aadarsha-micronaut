package com.micronaut.controller;


import com.micronaut.domain.PingInfo;
import com.micronaut.domain.PingResponse;
import com.micronaut.repository.AadarshaRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Validated
@Controller("/ping")
public class AadarshaController {

    private static final Logger logger = LoggerFactory.getLogger(AadarshaController.class);

    protected final AadarshaRepository aadarshaRepository;

    public AadarshaController(AadarshaRepository aadarshaRepository) {
        this.aadarshaRepository = aadarshaRepository;
    }


    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<PingResponse> ping() {
        logger.info("Invoked ping");
        PingResponse pingResponse = new PingResponse();
        pingResponse.setMessage("Ok I'm Up!");
        return HttpResponse.ok(pingResponse);
    }

    @Post(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<PingInfo> savePing(@Body @Valid PingInfo pingInfo) {
        //TODO: Call postgres repo to persist
        pingInfo.setPingTime(LocalDateTime.now());
        aadarshaRepository.save(pingInfo);
        return HttpResponse.ok(pingInfo);
    }
}
