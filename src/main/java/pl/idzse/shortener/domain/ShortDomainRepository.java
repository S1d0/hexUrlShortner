package pl.idzse.shortener.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ShortDomainRepository extends JpaRepository<ShortDomain, Long> {
}
