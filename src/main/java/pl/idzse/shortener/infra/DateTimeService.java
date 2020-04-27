package pl.idzse.shortener.infra;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Simple wrapper class, for simplify tests
 */
public enum DateTimeService {
    INSTANCE(LocalDateTime.now());
    private final LocalDateTime requestDateTime;

    DateTimeService(LocalDateTime dateTime) {
        this.requestDateTime = dateTime;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public LocalDate getRequestDate() {
        return requestDateTime.toLocalDate();
    }
}
