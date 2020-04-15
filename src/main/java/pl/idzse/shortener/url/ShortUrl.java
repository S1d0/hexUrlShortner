package pl.idzse.shortener.url;

import lombok.*;
import org.hibernate.annotations.*;
import pl.idzse.shortener.domain.ShortDomain;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NaturalIdCache
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    Long id;
    String originalUrl;

    @NaturalId(mutable = true)
    String shortUrl;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column
    @Setter(AccessLevel.PRIVATE)
    LocalDateTime createDateTime;

    @UpdateTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column
    @Setter(AccessLevel.PRIVATE)
    LocalDateTime modifyDateTime;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    ShortDomain domain;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl other = (ShortUrl) o;
        return Objects.equals(shortUrl, other.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortUrl);
    }

    ShortUrlDto getDto() {
        return new ShortUrlDto(shortUrl);
    }
    OriginalUrlDto getOriginalDto() {
        return new OriginalUrlDto(originalUrl);
    }
}
