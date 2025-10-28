package org.example.springdatajpa4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class JobHistoryId implements Serializable {
    private Long employee;  // Employee의 ID
    private LocalDate startDate;

    public JobHistoryId() {}

    public JobHistoryId(Long employee, LocalDate startDate) {
        this.employee = employee;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, startDate);
    }
}
