package pl.idzse.shortener.domain;

public class ShortDomainCreator {

    public static String create(String domain) {
        String funDomain;
        if ("facebook.com".equals(domain)) {
            funDomain = "jetson/";
        } else {
            funDomain = "flinstons/";
        }
        return funDomain;
    }
}
