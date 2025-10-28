package org.example.springdatajpa4;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    private Long id;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;
    @Column(name = "state_province")
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
