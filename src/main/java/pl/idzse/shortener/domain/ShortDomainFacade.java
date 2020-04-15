package pl.idzse.shortener.domain;

public class ShortDomainFacade {

    public ShortDomain createShortDomain(String domain) {
        ShortDomain shortDomain = ShortDomain.builder()
                .originalDomain(domain)
                .shortDomain(ShortDomainCreator.create())
                .build();

        return shortDomain;
    }
}
