package ua.com.hedgehog.adspublisher.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Campaign")
public class Campaign {
    @ApiModelProperty("Campaign id")
    private int id;
    @ApiModelProperty("Campaign name")
    private String name;
    @ApiModelProperty("Campaign status")
    private Status status;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign start date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign end date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-12-31 23:59:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @ApiModelProperty("Campaign's ads")
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
