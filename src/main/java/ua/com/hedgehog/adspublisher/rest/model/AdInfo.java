package ua.com.hedgehog.adspublisher.rest.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;

@ApiModel("Ad info")
public class AdInfo {
    @ApiModelProperty("Ad id")
    private final int id;
    @ApiModelProperty("Ad name")
    private final String name;
    @ApiModelProperty("Ad status")
    private final Status status;
    @ApiModelProperty("Ad platforms")
    private final EnumSet<Platform> platforms = EnumSet.noneOf(Platform.class);
    @ApiModelProperty(value = "Ad asset url", example = "http://site.com")
    private final String assetUrl;
    @ApiModelProperty("Ad campaigns")
    private final Set<Integer> campaigns = new HashSet<>();

    private AdInfo(Ad ad) {
        id = ad.getId();
        name = ad.getName();
        status = ad.getStatus();
        assetUrl = ad.getAssetUrl().toString();
        ad.getPlatforms().forEach(platforms::add);
        ad.getCampaigns().forEach(campaigns::add);
    }

    public static AdInfo of(Ad ad) {
        return new AdInfo(ad);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public EnumSet<Platform> getPlatforms() {
        return platforms;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public Set<Integer> getCampaigns() {
        return campaigns;
    }
}
