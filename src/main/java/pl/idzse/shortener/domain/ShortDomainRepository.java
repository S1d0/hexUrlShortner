package pl.idzse.shortener.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortDomainRepository extends JpaRepository<ShortDomain, Long> {
}
