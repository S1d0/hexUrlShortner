package pl.idzse.shortener.url;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    Long id;
    String originalUrl;

    @NaturalId(mutable = true)
    String shortedUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl1 = (ShortUrl) o;
        return Objects.equals(shortedUrl, shortUrl1.shortedUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortedUrl);
    }

    ShortUrlDto getDto() {
        return new ShortUrlDto(shortedUrl);
    }
    OriginalUrlDto getOriginalDto() {
        return new OriginalUrlDto(originalUrl);
    }
}
