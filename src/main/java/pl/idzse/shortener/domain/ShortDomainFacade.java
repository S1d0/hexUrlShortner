package pl.idzse.shortener.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ShortDomainFacade {
    private final ShortDomainTxService txService;

    @Transactional
    public ShortDomain createShortDomain(String domain) {
        return txService
                .findByNaturalId(domain)
                .orElseGet(() -> txService.save(new ShortDomain(domain)));
    }
}
