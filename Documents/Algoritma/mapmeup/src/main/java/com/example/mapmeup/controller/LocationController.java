package com.example.mapmeup.controller;

import com.example.mapmeup.model.Location;
import com.example.mapmeup.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid; // <--- PERUBAHAN PENTING: Tambahkan import ini

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping 
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return new ResponseEntity<>(location.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found with id: " + id);
        }
    }

    @PostMapping // Menambahkan lokasi baru
    // <--- PERUBAHAN PENTING: Tambahkan @Valid di sini
    public ResponseEntity<Location> addLocation(@Valid @RequestBody Location location) {
        try {
            Location savedLocation = locationRepository.save(location);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Cetak stack trace ke console serve
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @Valid @RequestBody Location locationDetails) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            Location existingLocation = location.get();
            existingLocation.setUsername(locationDetails.getUsername());
            existingLocation.setLatitude(locationDetails.getLatitude());
            existingLocation.setLongitude(locationDetails.getLongitude());
            existingLocation.setLocationName(locationDetails.getLocationName());
            existingLocation.setFullAddress(locationDetails.getFullAddress());

            Location updatedLocation = locationRepository.save(existingLocation);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}") // Menghapus lokasi berdasarkan ID
    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable Long id) {
        try {
            locationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}