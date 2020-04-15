package pl.idzse.shortener.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data()
@Entity
public class ShortDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    Long id;

    @Column
    String shortDomain;

    @Column
    String originalDomain;

    @Setter(AccessLevel.PRIVATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column
    LocalDateTime createDateTime;

    @Setter(AccessLevel.PRIVATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column
    LocalDateTime modifyDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortDomain that = (ShortDomain) o;
        return shortDomain.equals(that.shortDomain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortDomain);
    }
}
