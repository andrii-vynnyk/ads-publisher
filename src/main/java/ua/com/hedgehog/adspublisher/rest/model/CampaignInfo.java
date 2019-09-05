package ua.com.hedgehog.adspublisher.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.hedgehog.adspublisher.model.Status;

@ApiModel("Campaign info")
public class CampaignInfo {
    @ApiModelProperty("Campaign id")
    private final int id;
    @ApiModelProperty("Campaign name")
    private final String name;
    @ApiModelProperty("Campaign status")
    private final Status status;
    @ApiModelProperty("Campaign's ads quantity")
    private final int ads;

    public CampaignInfo(int id, String name, Status status, int adsSize) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.ads = adsSize;
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

    public int getAds() {
        return ads;
    }
}
