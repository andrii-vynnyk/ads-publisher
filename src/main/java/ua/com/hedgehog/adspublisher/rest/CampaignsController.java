package ua.com.hedgehog.adspublisher.rest;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;
import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.model.CampaignInfo;
import ua.com.hedgehog.adspublisher.rest.model.CampaignRequest;

@RestController
@Api(tags = "Campaigns")
public class CampaignsController {
    @Autowired
    private JdbcCampaignDAO campaignDao;

    @GetMapping("/summaries")
    @ApiOperation("Method is used to get an infos about all existing campaigns. You can use a pagination, a sorting and a filtering")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataTypeClass = Integer.class, paramType = "query", value = "Defines the initial page number"),
            @ApiImplicitParam(name = "size", dataTypeClass = Integer.class, paramType = "query", value = "Number of records to retrieve"),
            @ApiImplicitParam(name = "sortBy", dataTypeClass = String.class, paramType = "query", value = "Sort response results by name, status or ads quantity. Available values:  NAME, STATUS, ADS"),
            @ApiImplicitParam(name = "sortDir", dataTypeClass = String.class, paramType = "query", value = "Direction of the sorting. Available values:  ASC, DESC"),
            @ApiImplicitParam(name = "name", dataTypeClass = String.class, paramType = "query", value = "Filter by name"),
            @ApiImplicitParam(name = "status", dataTypeClass = String.class, paramType = "query", value = "Filter by status. Available values:  PLANNED, ACTIVE, PAUSED, FINISHED"),
    })
    public List<CampaignInfo> summaries(@ApiIgnore @RequestParam Map<String, String> params) {
        String page = params.get("page");
        String size = params.get("size");
        String sortBy = params.get("sortBy");
        String sortDir = params.get("sortDir");
        String name = params.get("name");
        String status = params.get("status");
        return campaignDao.findAll(
                val(page, () -> Integer.parseInt(page) - 1),
                val(size, () -> Integer.parseInt(size)),
                val(sortBy, () -> SortCampaign.valueOf(sortBy.toUpperCase())),
                val(sortDir, () -> SortDirection.valueOf(sortDir.toUpperCase())),
                val(name, () -> name),
                val(status, () -> Status.valueOf(status.toUpperCase())));
    }

    @GetMapping("/campaign/{id}")
    @ApiOperation("Method is used to get an existing campaign by a campaign's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved campaign"),
            @ApiResponse(code = 404, message = "The campaign you were trying to reach is not found")
    })
    public Campaign getCampaign(@PathVariable("id") int id) {
        return campaignDao.find(id);
    }

    @PostMapping("/campaign")
    @ApiOperation("Method is used to create a new campaign")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully inserted campaign"),
            @ApiResponse(code = 400, message = "The input parameters are not valid")
    })
    public Campaign insertCampaign(@ApiParam(name = "body", required = true) @Validated @RequestBody CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaignDao.insert(campaign);
        return campaign;
    }

    @PutMapping("campaign/{id}")
    @ApiOperation("Method is used to update an existing campaign")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated campaign"),
            @ApiResponse(code = 400, message = "The input parameters are not valid")
    })
    public Campaign updateCampaign(@ApiParam(name = "body", required = true) @Validated @RequestBody CampaignRequest request, @PathVariable("id") int id) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setId(id);
        campaignDao.update(campaign);
        return campaign;
    }

    @DeleteMapping("campaign/{id}")
    @ApiOperation("Method is used to delete an existing campaign")
    public void deleteCampaign(@PathVariable("id") int id) {
        campaignDao.delete(id);
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
