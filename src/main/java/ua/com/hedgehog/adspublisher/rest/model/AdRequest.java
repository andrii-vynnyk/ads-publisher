package ua.com.hedgehog.adspublisher.rest.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;

@ApiModel("Ad model")
public class AdRequest {
    @ApiModelProperty("Ad name")
    @NotBlank(message = "Name of ad can't be null or empty")
    private String name;
    @ApiModelProperty("Ad status")
    @NotNull(message = "Status of ad can't be null")
    private Status status;
    @ApiModelProperty("Ad platforms")
    @NotEmpty(message = "Set platform of ad")
    private EnumSet<Platform> platforms = EnumSet.noneOf(Platform.class);
    @ApiModelProperty(value = "Ad asset url", example = "http://site.com")
    @NotBlank(message = "Set asset url of ad")
    private String assetUrl;
    @ApiModelProperty("Ad campaigns")
    @NotEmpty(message = "Set campaigns for ad")
    private Set<Integer> campaigns = new HashSet<>();

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
