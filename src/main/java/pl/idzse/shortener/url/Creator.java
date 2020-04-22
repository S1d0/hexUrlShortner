package pl.idzse.shortener.url;

public class Creator {
    private static final long BASE62 = 62;
    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static String getBase62From10(long idx) {
        StringBuilder builder = new StringBuilder();
        if(idx < 62) {
            return   String.valueOf(ALL_CHAR.charAt(Math.toIntExact(idx)));
        }
        return getRecursiveBase62(idx, builder).reverse().toString();
    }

    private static StringBuilder getRecursiveBase62(long idx, StringBuilder builder) {
        if(idx < 1) {
            return builder;
        }
        int charIndex = Math.toIntExact(idx % BASE62);
        builder.append(ALL_CHAR.charAt(charIndex));
        long newIdx = idx / BASE62;
        return getRecursiveBase62(newIdx, builder);
    }
}
