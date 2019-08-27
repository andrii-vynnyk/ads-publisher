package ua.com.hedgehog.adspublisher.rest.exception;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException(int id) {
        super("Campaign id not found : " + id);
    }

}