package org.nogs.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.conversions.Bson;
import org.nogs.model.internal.Event;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EventRepository {

    @Inject
    MongoClient mongoClient;

    private MongoCollection<Event> collection() {
        return mongoClient
                .getDatabase("reservations")
                .getCollection("events", Event.class);
    }

    // TODO: review this function. There is definitely a way of doing this more professionally
    public List<Event> findAll(String title, String venueId, String afterDate, String beforeDate) {
        var filters = new ArrayList<Bson>();

        if (title != null && !title.isBlank()) {
            filters.add(Filters.eq("title", title));
        }
        if (venueId != null && !venueId.isBlank()) {
            filters.add(Filters.eq("venueId", venueId));
        }
        if (afterDate != null && !afterDate.isBlank()) {
            filters.add(Filters.gt("startTime", afterDate));
        }
        if (beforeDate != null && !beforeDate.isBlank()) {
            filters.add(Filters.gt("endTime", beforeDate));
        }

        if (filters.isEmpty()) {
            return collection().find().into(new ArrayList<>());
        } else {
            return collection()
                    .find(Filters.and(filters))
                    .into(new ArrayList<>());
        }
    }

    public void insert(Event event) {
        collection().insertOne(event);
    }
}