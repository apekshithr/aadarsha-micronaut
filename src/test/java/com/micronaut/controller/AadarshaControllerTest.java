package com.micronaut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micronaut.domain.PingResponse;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AadarshaControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;
    private ObjectMapper mapper;


    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void testPing() throws IOException {
        HttpRequest request = HttpRequest.GET("/ping");
        String body = client.toBlocking().retrieve(request);
        assertNotNull(body);
        PingResponse response = mapper.readValue(body, PingResponse.class);
        assertEquals("Ok I'm Up!", response.getMessage());
    }
}
