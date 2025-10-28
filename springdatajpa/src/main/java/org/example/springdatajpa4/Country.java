package org.example.springdatajpa4;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id")
    private String id;
    @Column(name = "country_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
