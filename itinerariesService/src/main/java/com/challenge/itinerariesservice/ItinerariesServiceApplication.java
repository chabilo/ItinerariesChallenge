package com.challenge.itinerariesservice;

import com.challenge.itinerariesservice.model.Itinerary;
import com.challenge.itinerariesservice.repository.ItinerariesRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan(basePackageClasses = SubscriptionController.class)
public class ItinerariesServiceApplication {
    

    public static void main(String[] args) {
        SpringApplication.run(ItinerariesServiceApplication.class, args);
    }
    
//  public void run(String args[]) {
//
//    final Itinerary johnAoe = new Itinerary("Zaragoza", "Madrid", LocalDate.now(), LocalDate.now());
//    final Itinerary johnBoe = new Itinerary("Madrid", "Sevilla", LocalDate.now(), LocalDate.now());
//    final Itinerary johnCoe = new Itinerary("john", "coe", LocalDate.now(), LocalDate.now());
//    final Itinerary johnDoe = new Itinerary("john", "doe", LocalDate.now(), LocalDate.now());
//
//    itinerariesRepository.save(johnAoe);
//        itinerariesRepository.save(johnBoe);
//    itinerariesRepository.save(johnCoe);
//
//
//    itinerariesRepository.findAll()
//        .log()
//        .map(Itinerary::getOriginCity)
//        .subscribe(System.out::println);
//}
}
