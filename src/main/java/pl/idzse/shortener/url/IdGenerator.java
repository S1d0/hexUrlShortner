package pl.idzse.shortener.url;

public class IdGenerator {
    public static Long uniqeId = 1L;

    public static Long getUniqeId() {
        return uniqeId ++;
    };
}
