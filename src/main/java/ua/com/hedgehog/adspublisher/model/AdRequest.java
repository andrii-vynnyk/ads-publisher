package ua.com.hedgehog.adspublisher.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@ApiModel("Ad request")
public class AdRequest {
    @ApiModelProperty("Ad name")
    @NotBlank(message = "Name of ad can't be null or empty")
    protected String name;
    @ApiModelProperty("Ad status")
    @NotNull(message = "Status of ad can't be null")
    protected Status status;
    @ApiModelProperty("Ad platforms")
    @NotEmpty(message = "Set platform of ad")
    protected EnumSet<Platform> platforms = EnumSet.noneOf(Platform.class);
    @ApiModelProperty(dataType = "java.lang.String", value = "Ad asset url", example = "http://site.com")
    @NotNull(message = "Set asset url of ad")
    protected URL assetUrl;
    @ApiModelProperty("Ad campaigns")
    @NotEmpty(message = "Set campaigns for ad")
    protected Set<Integer> campaigns = new HashSet<>();

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

    public void setPlatforms(EnumSet<Platform> platforms) {
        this.platforms = platforms;
    }

    public URL getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(URL assetUrl) {
        this.assetUrl = assetUrl;
    }

    public Set<Integer> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Integer> campaigns) {
        this.campaigns = campaigns;
    }

    public void addPlatform(Platform platform) {
        if (platforms == null) {
            platforms = EnumSet.of(platform);
        } else {
            platforms.add(platform);
        }
    }

    public void addCampaign(Integer campaignId) {
        campaigns.add(campaignId);
    }
}
