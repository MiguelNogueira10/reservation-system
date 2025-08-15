package org.nogs.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nogs.model.external.EventExternal;
import org.nogs.model.internal.Event;
import org.nogs.translator.EventTranslator;

import java.util.List;
import java.util.stream.Collectors;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @GET
    public List<EventExternal> getAll() {
        return Event.<Event>listAll().stream()
                .map(EventTranslator::toExternal)
                .collect(Collectors.toList());
    }

    @POST
    public Response create(EventExternal eventExternal) {
        Event event = EventTranslator.fromExternal(eventExternal);
        event.persist();
        return Response.status(Response.Status.CREATED).entity(EventTranslator.toExternal(event)).build();
    }
}
