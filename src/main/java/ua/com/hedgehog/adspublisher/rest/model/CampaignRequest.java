package ua.com.hedgehog.adspublisher.rest.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.hedgehog.adspublisher.model.Status;

@ApiModel("Campaign model")
public class CampaignRequest {
    @ApiModelProperty("Campaign name")
    @NotBlank(message = "Name of campaign can't be null or empty")
    private String name;
    @ApiModelProperty("Campaign status")
    @NotNull(message = "Status of campaign can't be null")
    private Status status;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign start date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotBlank(message = "Start date of campaign can't be null or blank")
    private LocalDateTime startDate;
    @ApiModelProperty(dataType = "java.lang.String", value = "Campaign end date. Date format: \"yyyy-MM-dd HH:mm:ss\"", example = "2019-12-31 23:59:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotBlank(message = "End date of campaign can't be null or blank")
    private LocalDateTime endDate;

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
}
