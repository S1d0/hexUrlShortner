package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pl.idzse.shortener.domain.ShortDomain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class TransactionalService {
    private final ShortUrlCreator creator = new ShortUrlCreator();

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    ShortUrl save(ShortUrl shortUrl) {
        entityManager.persist(shortUrl);
        shortUrl.setShortUrl(creator.getShortUrl(shortUrl.id));
        return shortUrl;
    }
}
