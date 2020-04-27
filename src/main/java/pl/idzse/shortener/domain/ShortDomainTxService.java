package pl.idzse.shortener.domain;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ShortDomainTxService {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    ShortDomain save(ShortDomain shortDomain) {
        entityManager.persist(shortDomain);
        return shortDomain;
    }

    public Optional<ShortDomain> findByNaturalId(String domain) {
        Session session = entityManager.unwrap(Session.class);
        return session.byNaturalId(ShortDomain.class)
                .using("originalDomain", domain)
                .loadOptional();
    }
}
