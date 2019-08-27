package ua.com.hedgehog.adspublisher.model;

public enum Platform {
    WEB, ANDROID, IOS;

    public static Platform byId(int id) {
        if (id < 0 || id >= values().length) {
            throw new EnumConstantNotPresentException(Platform.class, "Out of range: id = " + id);
        }
        return values()[id];
    }
}
