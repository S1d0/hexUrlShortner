package pl.idzse.shortener.url;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import pl.idzse.shortener.domain.ShortDomain;
import pl.idzse.shortener.infra.DateTimeService;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NaturalIdCache
@Data
class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    Long id;
    String originalUrl;

    @NaturalId(mutable = true)
    String shortUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOMAIN_ID", nullable = false)
    ShortDomain domain;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    LocalDateTime creation;

    public ShortUrl() {

    }

    ShortUrl(String url, ShortDomain shortDomain) {
        this.originalUrl = url;
        this.domain = shortDomain;
        this.creation = DateTimeService.INSTANCE.getRequestDateTime();
    }

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
        String shortPath = "http://" + domain.getShortDomain() + shortUrl;
        return new ShortUrlDto(shortUrl, domain.getShortDomain(), shortPath);
    }

    OriginalUrlDto getOriginalDto() {
        return new OriginalUrlDto(originalUrl);
    }
}
