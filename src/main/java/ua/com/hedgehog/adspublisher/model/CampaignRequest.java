package ua.com.hedgehog.adspublisher.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel("Campaign request")
public class CampaignRequest {
    @ApiModelProperty("Campaign name")
    @NotBlank(message = "Name of campaign can't be null or empty")
    protected String name;
    @ApiModelProperty("Campaign status")
    @NotNull(message = "Status of campaign can't be null")
    protected Status status;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign start date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Start date of campaign can't be null or blank")
    protected LocalDateTime startDate;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign end date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-12-31 23:59:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "End date of campaign can't be null or blank")
    protected LocalDateTime endDate;

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
}
