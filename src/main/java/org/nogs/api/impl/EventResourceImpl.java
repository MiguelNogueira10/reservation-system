package org.nogs.api.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.nogs.model.internal.Event;
import org.nogs.repository.EventRepository;

import java.util.List;

@ApplicationScoped
public class EventResourceImpl {

    @Inject
    EventRepository repository;

    public List<Event> getFilteredEvents(String title, String venueId, String afterDate, String beforeDate) {
        return repository.findAll(title, venueId, afterDate, beforeDate);
    }

    public void createEvent(Event event) {
        repository.insert(event);
    }
}
