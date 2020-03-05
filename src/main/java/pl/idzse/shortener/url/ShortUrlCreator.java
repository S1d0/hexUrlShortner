package pl.idzse.shortener.url;

public class ShortUrlCreator {
    private final long BASE62 = 62;
    private String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String getBase62From10(long idx) {
        StringBuilder builder = new StringBuilder();
        if(idx < 62) {
           return   String.valueOf(ALL_CHAR.charAt(Math.toIntExact(idx)));
        }
        return getRecursiveBase62(idx, builder).reverse().toString();
    }

    Long getBase10From62(String base62) {
        if(base62.length() < 2) {
           int idx = ALL_CHAR.indexOf(base62);
           return (long) idx;
        }
        Long base10 = 0L;
        return Long.valueOf(getRecursiveBase10(base62, base10));
    }

    private String getRecursiveBase10(String base62, Long base10) {
        if(base62.length() == 0) {
            return String.valueOf(base10);
        }
        int baseNumb = ALL_CHAR.indexOf(base62.charAt(0));
        double b = baseNumb * Math.pow(BASE62, base62.length()-1);
        return getRecursiveBase10(base62.substring(1), base10+(int)b);
    }


    private StringBuilder getRecursiveBase62(long idx, StringBuilder builder) {
        if(idx < 1) {
            return builder;
        }
        int charIndex = Math.toIntExact(idx % BASE62);
        builder.append(ALL_CHAR.charAt(charIndex));
        long newIdx = idx / BASE62;
        return getRecursiveBase62(newIdx, builder);
    }

    public String getShortUrl(long idx) {
        String HTTPS = "https://";
        return HTTPS + getBase62From10(idx);
    }
}
