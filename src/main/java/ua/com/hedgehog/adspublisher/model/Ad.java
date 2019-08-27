package ua.com.hedgehog.adspublisher.model;

import java.net.URL;
import java.util.EnumSet;

public class Ad {
    private int id;
    private String name;
    private Status status;
    private EnumSet<Platform> platforms;
    private URL assetUrl;

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

    public EnumSet<Platform> getPlatforms() {
        return platforms;
    }

    public void addPlatform(Platform platform) {
        if (platforms == null) {
            platforms = EnumSet.of(platform);
        } else {
            platforms.add(platform);
        }
    }

    public void removePlatform(Platform platform) {
        if (platforms != null) {
            platforms.remove(platform);
        }
    }

    public URL getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(URL assetUrl) {
        this.assetUrl = assetUrl;
    }
}
