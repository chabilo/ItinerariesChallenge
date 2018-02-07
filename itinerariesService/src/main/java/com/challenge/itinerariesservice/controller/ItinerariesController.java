package com.challenge.itinerariesservice.controller;

import org.bson.types.ObjectId;
import com.challenge.itinerariesservice.model.Itinerary;
import com.challenge.itinerariesservice.exception.ItinerariesServiceException;
import com.challenge.itinerariesservice.repository.ItinerariesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/itineraries", produces = {TEXT_PLAIN_VALUE})
public class ItinerariesController {

    private static final Logger log = LoggerFactory.getLogger(ItinerariesController.class);

    private ItinerariesRepository repository;

    public ItinerariesController(ItinerariesRepository repository) {
        this.repository = repository;
    }

    /**
     * Query for all itineraries.
     * <p>
     * This method is idempotent.
     *
     * @return HTTP 200 if customers found or HTTP 204 otherwise.
     */
//	@PreAuthorize("#oauth2.hasAnyScope('read','write','read-write')")
    @RequestMapping(method = GET)
    public Mono<ResponseEntity<List<Itinerary>>> getAllItineraries() {
        return repository.findAll().collectList()
                .filter(itineraries -> itineraries.size() > 0)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(noContent().build());
    }

    /**
     * Query for a itinerary with the given Id.
     * <p>
     * This method is idempotent.
     *
     * @param id The id of the customer to look for.
     * @return HTTP 200 if the customer is found or HTTP 404 otherwise.
     */
    public Mono<ResponseEntity<Itinerary>> getItinerary(@PathVariable @NotNull ObjectId id) {
        return repository.findById(id)
                .map(customer -> ok().contentType(APPLICATION_JSON_UTF8).body(customer))
                .defaultIfEmpty(notFound().build());
    }

    /**
     * Create a new itinerary.
     *
     * @param newSubscription The subscription to create.
     * @return HTTP 201, the header Location contains the URL of the created subscription.
     */
    //@PreAuthorize("#oauth2.hasAnyScope('write','read-write')")
//    @RequestMapping(method = POST, consumes = {APPLICATION_JSON_UTF8_VALUE})
//    public Mono<ResponseEntity<?>> addSubscription(@RequestBody @Valid Itinerary itinerary) {
//
//        return Mono.justOrEmpty(itinerary.getOriginCity())
//                .flatMap(( origin,  destiny)-> repository.existsByOriginDestiny(origin, destiny))
//                .defaultIfEmpty(Boolean.FALSE)
//                .flatMap(exists -> {
//                    log.info("exists vale: " +  exists);
//                    if (exists) {
//                        throw new ItinerariesServiceException(HttpStatus.BAD_REQUEST, "Itinerary already exists.");
//                    }
//                    return repository
//                            .save(itinerary)
//                            .map(saved -> created(URI.create(String.format("/itineraries/%s", saved.getId()))).build());
//                });
//    }

    /**
     * Delete a itinerary.
     * <p>
     * This method is idempotent, if it's called multiples times with the same
     * id then the first call will delete the customer and subsequent calls will
     * be silently ignored.
     *
     * @param id The id of the itinerary to delete.
     * @return HTTP 204
     */
    public Mono<ResponseEntity<?>> deleteItinerary(@PathVariable @NotNull ObjectId id) {

        final Mono<ResponseEntity<?>> noContent = Mono.just(noContent().build());

        return repository.existsById(id)
                .filter(Boolean::valueOf)
                .flatMap(exists -> repository.deleteById(id).then(noContent))
                .switchIfEmpty(noContent);
    }
}
