package ua.com.hedgehog.adspublisher.rest.exception;

public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException(int id) {
        super("Ad id not found : " + id);
    }
}