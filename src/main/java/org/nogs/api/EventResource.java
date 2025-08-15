package org.nogs.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nogs.api.impl.EventResourceImpl;
import org.nogs.model.dto.EventDTO;
import org.nogs.model.internal.Event;
import org.nogs.api.translator.EventTranslator;

import java.util.List;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @Inject
    EventResourceImpl eventService;

    @GET
    public List<EventDTO> getAll(
            @QueryParam("title") String title,
            @QueryParam("venueId") String venueId,
            @QueryParam("after") String afterDate,
            @QueryParam("before") String beforeDate
    ) {
        return eventService.getFilteredEvents(title, venueId, afterDate, beforeDate)
                .stream()
                .map(EventTranslator::toDTO)
                .toList();
    }

    @POST
    public Response create(EventDTO dto) {
        Event event = EventTranslator.fromDTO(dto);
        eventService.createEvent(event);
        return Response.status(Response.Status.CREATED)
                .entity(EventTranslator.toDTO(event))
                .build();
    }
}
