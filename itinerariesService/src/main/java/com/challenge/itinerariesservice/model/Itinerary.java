package com.challenge.itinerariesservice.model;

import org.bson.types.ObjectId;
import com.challenge.itinerariesservice.exception.ItinerariesServiceException;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * {@code Itinerary}
 */
public final class Itinerary {

	private ObjectId id;

	private String originCity;

	private String destinyCity;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate departureTime;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate arrivalTime;

	private Itinerary() {
	}

	public Itinerary(String originCity, String destinyCity, LocalDate departureTime, LocalDate arrivalTime) {

		if (originCity == null) {
			throw new ItinerariesServiceException(BAD_REQUEST, "Origin city can not be null.");
		}

		if (destinyCity == null) {
			throw new ItinerariesServiceException(BAD_REQUEST, "Destiny city can not be null.");
		}

		if (departureTime == null) {
			throw new ItinerariesServiceException(BAD_REQUEST, "Departure time can not be null.");
		}
                
		if (arrivalTime == null) {
			throw new ItinerariesServiceException(BAD_REQUEST, "Arrival time can not be null.");
		}

                this.originCity = originCity;
		this.destinyCity = destinyCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public ObjectId getId() {
		return id;
	}

        public String getOriginCity() {
            return originCity;
        }

	public String getDestinyCity() {
            return destinyCity;
	}

        public LocalDate getDepartureTime() {
            return departureTime;
        }
        
        public LocalDate getArrivalTime() {
            return arrivalTime;
        }

}
