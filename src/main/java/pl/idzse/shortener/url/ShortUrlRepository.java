package pl.idzse.shortener.url;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.idzse.shortener.domain.ShortDomain;

import javax.swing.text.html.Option;
import java.util.Optional;

interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findByShortUrl(String shortedUrl);

    Optional<ShortUrl> findByOriginalUrlAndDomain(String originalUrl, ShortDomain domain);
}
