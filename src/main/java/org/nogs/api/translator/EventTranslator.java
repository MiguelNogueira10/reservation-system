package org.nogs.api.translator;

import org.nogs.model.dto.EventDTO;
import org.nogs.model.internal.Event;
import java.time.LocalDateTime;

public class EventTranslator {

    public static EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.title = event.title;
        dto.startTime = event.startTime.toString();
        dto.description = event.description;
        dto.venueId = event.venueId;
        return dto;
    }

    public static Event fromDTO(EventDTO dto) {
        Event event = new Event();
        event.title = dto.title;
        event.startTime = LocalDateTime.parse(dto.startTime);
        event.description = dto.description;
        event.venueId = dto.venueId;
        return event;
    }
}
