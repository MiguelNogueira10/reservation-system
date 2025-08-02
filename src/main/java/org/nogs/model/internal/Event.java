package org.nogs.model.internal;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "events")
public class Event extends PanacheMongoEntity {

    public String title;
    public LocalDateTime startTime;
    public String description;
    public String venueId;  // Reference to Venue collection
}