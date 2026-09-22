package com.ga.travel.Travel.Controller;


import com.ga.travel.Travel.Model.Destination;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TravelController {

    private final List<Destination> destinations = new ArrayList<>();
    public TravelController() {

        destinations.add(new Destination(
                1,
                "Tokyo",
                "Japan",
                "Asia",
                "City",
                4.1,
                "Cheap",
                "A city famous for technology, seafood and culture."
        ));

        destinations.add(new Destination(
                2,
                "Bali",
                "Indonesia",
                "Asia",
                "Beach",
                2.4,
                "Moderate",
                "A destination famous for beaches and temples."
        ));

        destinations.add(new Destination(
                3,
                "Rome",
                "Italy",
                "Europe",
                "Historical",
                5,
                "Expensive",
                "A historic city famous for ancient Roman landmarks."
        ));

        destinations.add(new Destination(
                4,
                "Interlaken",
                "Switzerland",
                "Europe",
                "Adventure",
                5,
                "Most Expensive",
                "A beautiful destination surrounded by mountains,lakes and waterfalls."
        ));

        destinations.add(new Destination(
                5,
                "Makkah",
                "Saudi Arabia",
                "Asia",
                "Religious",
                5.0,
                "Moderate",
                "religious destination for all muslims."
        ));

        destinations.add(new Destination(
                6,
                "Paris",
                "France",
                "Europe",
                "City",
                3.8,
                "Expensive",
                "A famous city known for art, bakery and architecture."
        ));
    }

    @GetMapping("/travel")
    public Map<String, Object> welcome() {

        Map<String, Object> profile = new HashMap<>();

        profile.put("application", "Travel Explorer API");
        profile.put("name", "Ali");
        profile.put("role", "Travel Explorer");
        profile.put("theme", "Travel");
        profile.put("introduction",
                "I enjoy travelling to new countries and explore their cultures and food.");
        profile.put("favoriteDestination", "Switzerland");
        profile.put("currentlyLearning", "REST APIs");
        profile.put("interestingFact", "I visited 17 countries.");


        return profile;
    }

    @GetMapping("/destinations")
    public List<Destination> getAllDestinations() {

        return destinations;
    }

    @GetMapping("/destinations/{id}")
    public ResponseEntity<?> getDestination(@PathVariable int id) {

        for (Destination destination : destinations) {

            if (destination.getId() == id) {
                return ResponseEntity.ok(destination);
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Destination not found");
    }

    @GetMapping("/destinations/search")
    public List<Destination> searchDestinations(
            @RequestParam String name) {

        List<Destination> results = new ArrayList<>();

        for (Destination destination : destinations) {

            if (destination.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                results.add(destination);
            }
        }

        return results;
    }

    @GetMapping("/destinations/filter")
    public List<Destination> filterDestinations(
            @RequestParam String category) {

        List<Destination> results = new ArrayList<>();

        for (Destination destination : destinations) {

            if (destination.getCategory()
                    .equalsIgnoreCase(category)) {

                results.add(destination);
            }
        }

        return results;
    }


    @PostMapping("/destinations")
    public ResponseEntity<Destination> createDestination(
            @RequestBody Destination destination) {

        int newId = destinations.stream()
                .mapToInt(Destination::getId)
                .max()
                .orElse(0) + 1;

        destination.setId(newId);

        destinations.add(destination);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(destination);
    }

    @PutMapping("/destinations/{id}")
    public ResponseEntity<?> updateDestination(
            @PathVariable int id,
            @RequestBody Destination updatedDestination) {

        for (Destination destination : destinations) {

            if (destination.getId() == id) {

                destination.setName(updatedDestination.getName());
                destination.setCountry(updatedDestination.getCountry());
                destination.setContinent(updatedDestination.getContinent());
                destination.setCategory(updatedDestination.getCategory());
                destination.setRating(updatedDestination.getRating());
                destination.setPriceLevel(updatedDestination.getPriceLevel());
                destination.setDescription(updatedDestination.getDescription());

                return ResponseEntity.ok(destination);
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Destination not found");
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<?> deleteDestination(
            @PathVariable int id) {

        boolean removed = destinations.removeIf(
                destination -> destination.getId() == id
        );

        if (removed) {

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Destination deleted successfully",
                            "deletedId", id
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Destination not found");
    }

    @GetMapping("/destinations/stats")
    public Map<String, Object> getStatistics() {

        int total = destinations.size();

        double totalRating = 0;

        for (Destination destination : destinations) {
            totalRating += destination.getRating();
        }

        double averageRating = 0;

        if (total > 0) {
            averageRating = totalRating / total;
        }

        Map<String, Object> statistics = new HashMap<>();

        statistics.put("totalDestinations", total);
        statistics.put(
                "averageRating",
                Math.round(averageRating * 100.0) / 100.0
        );

        return statistics;
    }

    @GetMapping("/destinations/random")
    public ResponseEntity<?> randomDestination() {

        if (destinations.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No destinations available");
        }

        int randomIndex =
                (int) (Math.random() * destinations.size());

        return ResponseEntity.ok(
                destinations.get(randomIndex)
        );
    }


}
