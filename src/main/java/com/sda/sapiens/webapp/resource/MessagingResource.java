package com.sda.sapiens.webapp.resource;

import com.sda.sapiens.webapp.messaging.MessageSender;
import com.sda.sapiens.webapp.messaging.Wiadomosc;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api
@Slf4j
@Stateless
@Path("/api/messaging")
public class MessagingResource {

    private final MessageSender messageSender;

    @Inject
    public MessagingResource(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @POST
    @Path("")
    public Response sendMessage(Wiadomosc wiadomosc){
        log.info("Wysylam: " + wiadomosc);
        messageSender.send(wiadomosc.getTresc());

        return Response.status(Response.Status.OK).build();
    }
}
