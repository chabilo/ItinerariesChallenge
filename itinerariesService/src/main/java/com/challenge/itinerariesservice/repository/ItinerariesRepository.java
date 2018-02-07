package com.challenge.itinerariesservice.repository;

import org.bson.types.ObjectId;
import com.challenge.itinerariesservice.model.Itinerary;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ItinerariesRepository extends ReactiveCrudRepository<Itinerary, ObjectId> {

    Mono<Boolean> existsByOriginDestiny(String origin, String destiny);

}
