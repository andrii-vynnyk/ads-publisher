package ua.com.hedgehog.adspublisher.service;

import lombok.Getter;
import lombok.Setter;
import ua.com.hedgehog.adspublisher.model.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class CampaignRequest {
    private String name;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
