package org.example.jpa;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Setter@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school; //연관 관계를 알려줄 필요가 있다.

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, School school) {
        this.name = name;
        this.school = school;
    }
}
