package ua.com.hedgehog.adspublisher.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Campaign")
public class Campaign extends CampaignRequest{
    @ApiModelProperty("Campaign id")
    private int id;

    @ApiModelProperty("Campaign's ads")
    private Map<Integer, Ad> ads = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
