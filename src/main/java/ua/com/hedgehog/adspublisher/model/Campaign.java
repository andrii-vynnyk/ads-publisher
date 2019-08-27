package ua.com.hedgehog.adspublisher.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Campaign {
    private int id;
    private String name;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Map<Integer, Ad> ads = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Collection<Ad> getAds() {
        return ads.values();
    }

    public void addAd(Ad ad) {
        ads.put(ad.getId(), ad);
    }

    public void remove(Ad ad) {
        ads.remove(ad.getId());
    }
}
