package org.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "job_id")
    private String id;
    @Column(name = "job_title")
    private String title;
    @Column(name = "max_salary")
    private Long maxSalary;
    @Column(name = "min_salary")
    private Long minSalary;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "job")
    private Set<JobHistory> jobHistories;


}
