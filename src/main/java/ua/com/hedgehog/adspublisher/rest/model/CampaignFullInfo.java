package ua.com.hedgehog.adspublisher.rest.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Status;

@ApiModel("Campaign full info")
public class CampaignFullInfo {
    @ApiModelProperty("Campaign id")
    private final int id;
    @ApiModelProperty("Campaign name")
    private final String name;
    @ApiModelProperty("Campaign status")
    private final Status status;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign start date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startDate;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign end date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-12-31 23:59:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endDate;
    @ApiModelProperty("Campaign's ads")
    private final Set<AdInfo> ads = new HashSet<>();

    private CampaignFullInfo(Campaign campaign) {
        id = campaign.getId();
        name = campaign.getName();
        status = campaign.getStatus();
        startDate = campaign.getStartDate();
        endDate = campaign.getEndDate();
        campaign.getAds().forEach(ad -> ads.add(AdInfo.of(ad)));
    }

    public static CampaignFullInfo of(Campaign campaign) {
        return new CampaignFullInfo(campaign);
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Set<AdInfo> getAds() {
        return ads;
    }
}
