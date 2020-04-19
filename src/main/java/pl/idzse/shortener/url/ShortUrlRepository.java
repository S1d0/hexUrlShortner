package pl.idzse.shortener.url;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findByShortUrl(String shortedUrl);
}
