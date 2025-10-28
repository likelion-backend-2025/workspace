package org.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "department_id")
    private Long id;
    @Column(name = "department_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "manager_id"/*, foreignKey = @ForeignKey(name = "FK_department_manager"),nullable = true*/)
    private Employee manager;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;


}
