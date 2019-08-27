package ua.com.hedgehog.adspublisher.model;

public enum Status {
    PLANNED, ACTIVE, PAUSED, FINISHED;

    public static Status byId(int id) {
        if (id < 0 || id >= values().length) {
            throw new EnumConstantNotPresentException(Status.class, "Out of range: id = " + id);
        }
        return values()[id];
    }
}
