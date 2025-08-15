package org.nogs.translator;

import org.nogs.model.external.EventExternal;
import org.nogs.model.internal.Event;
import java.time.LocalDateTime;

public class EventTranslator {

    public static EventExternal toExternal(Event event) {
        EventExternal eventExternal = new EventExternal();
        eventExternal.title = event.title;
        eventExternal.startTime = event.startTime.toString();
        eventExternal.description = event.description;
        eventExternal.venueId = event.venueId;
        return eventExternal;
    }

    public static Event fromExternal(EventExternal eventExternal) {
        Event event = new Event();
        event.title = eventExternal.title;
        event.startTime = LocalDateTime.parse(eventExternal.startTime);
        event.description = eventExternal.description;
        event.venueId = eventExternal.venueId;
        return event;
    }
}
