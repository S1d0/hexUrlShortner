package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
class TransactionalService {
    private final ShortUrlCreator creator = new ShortUrlCreator();

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    ShortUrl save(ShortUrl shortUrl) {
        entityManager.persist(shortUrl);
        shortUrl.setShortUrl(
                creator.getShortUrl(shortUrl.id)
        );
        return ShortUrl.builder()
                .id(shortUrl.id)
                .originalUrl(shortUrl.originalUrl)
                .shortUrl(shortUrl.shortUrl)
                .build();
    }
}
