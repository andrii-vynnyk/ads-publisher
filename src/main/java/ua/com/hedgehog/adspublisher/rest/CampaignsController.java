package ua.com.hedgehog.adspublisher.rest;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;
import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.service.CampaignInfo;
import ua.com.hedgehog.adspublisher.service.CampaignRequest;

import java.util.List;

@RestController
public class CampaignsController {
    @Autowired
    private JdbcCampaignDAO campaignDao;

    @GetMapping("/summaries")
    public List<CampaignInfo> summaries(@ApiParam(name = "page") @RequestParam(value = "page", required = false) String page,
                                        @ApiParam(name = "size") @RequestParam(value = "size", required = false) String size,
                                        @ApiParam(name = "sortBy") @RequestParam(value = "sortBy", required = false) String sortBy,
                                        @ApiParam(name = "sortDir") @RequestParam(value = "sortDir", required = false) String sortDir,
                                        @ApiParam(name = "name") @RequestParam(value = "name", required = false) String name,
                                        @ApiParam(name = "status") @RequestParam(value = "status", required = false) String status) {
        return campaignDao.findAll(
                val(page, () -> Integer.valueOf(page)),
                val(size, () -> Integer.valueOf(size)),
                val(sortBy, () -> SortCampaign.valueOf(sortBy.toUpperCase())),
                val(sortDir, () -> SortDirection.valueOf(sortDir.toUpperCase())),
                val(name, () -> name),
                val(status, () -> Status.valueOf(status.toUpperCase())));
    }

    @GetMapping("/campaign/{campaignId}")
    public Campaign getCampaign(@PathVariable("campaignId") int id) {
        return campaignDao.find(id);
    }

    @PostMapping("/campaign")
    public Campaign insertCampaign(@RequestBody CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaignDao.insert(campaign);
        return campaign;
    }

    private <T> T val(String val, ThrowableSupplier<T> supplier) {
        if (val == null) {
            return null;
        }
        try {
            return supplier.get();
        } catch (Throwable ignore) {

        }
        return null;
    }

    @FunctionalInterface
    private interface ThrowableSupplier<T> {
        T get() throws Throwable;
    }
}
