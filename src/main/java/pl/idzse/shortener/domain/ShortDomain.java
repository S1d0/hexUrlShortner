package pl.idzse.shortener.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import pl.idzse.shortener.infra.DateTimeService;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class ShortDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String shortDomain;

    @NaturalId
    @Column
    String originalDomain;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    LocalDateTime creation;

    public ShortDomain(){}

    ShortDomain(String originalDomain) {
        this.originalDomain = originalDomain;
        this.shortDomain = ShortDomainCreator.create(originalDomain);
        this.creation = DateTimeService.INSTANCE.getRequestDateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortDomain that = (ShortDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}