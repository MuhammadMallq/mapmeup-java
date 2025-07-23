package com.example.mapmeup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Tambahkan import ini
import jakarta.validation.constraints.NotNull;  // Tambahkan import ini
import jakarta.validation.constraints.Min;      // Tambahkan import ini
import jakarta.validation.constraints.Max;      // Tambahkan import ini

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Perubahan di sini: Tambah anotasi validasi dan Column ---
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull(message = "Latitude cannot be null")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    @Column(name = "latitude", nullable = false)
    private double latitude;

    @NotNull(message = "Longitude cannot be null")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    @Column(name = "longitude", nullable = false)
    private double longitude;
    // --- Akhir perubahan anotasi validasi ---

    // --- Perubahan di sini: Tambah properti baru ---
    @Column(name = "location_name") // Nama kolom di database, opsional jika sama dengan nama properti
    private String locationName;

    @Column(name = "full_address")
    private String fullAddress;
    // --- Akhir perubahan properti baru ---

    public Location() {}

    // --- Perubahan di sini: Perbarui konstruktor ---
    public Location(String username, double latitude, double longitude, String locationName, String fullAddress) {
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = locationName; // Inisialisasi properti baru
        this.fullAddress = fullAddress;   // Inisialisasi properti baru
    }
    // --- Akhir perubahan konstruktor ---

    // getter dan setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    // --- Perubahan di sini: Tambah getter dan setter untuk properti baru ---
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getFullAddress() { return fullAddress; }
    public void setFullAddress(String fullAddress) { this.fullAddress = fullAddress; }
    // --- Akhir perubahan getter dan setter baru ---

    // --- Perubahan di sini: Tambah metode toString() untuk debugging ---
    @Override
    public String toString() {
        return "Location{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", latitude=" + latitude +
               ", longitude=" + longitude +
               ", locationName='" + locationName + '\'' +
               ", fullAddress='" + fullAddress + '\'' +
               '}';
    }
}