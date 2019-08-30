package ua.com.hedgehog.adspublisher.rest;

import java.net.MalformedURLException;
import java.net.URL;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.hedgehog.adspublisher.db.JdbcAdDAO;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.rest.model.AdInfo;
import ua.com.hedgehog.adspublisher.rest.model.AdRequest;

@RestController
@Api(tags = "Ads")
public class AdsController {
    @Autowired
    private JdbcAdDAO adDao;

    @GetMapping("/ad/{id}")
    @ApiOperation("Method is used to get an existing ad by a ad's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated ad"),
            @ApiResponse(code = 404, message = "The ad you were trying to reach is not found")
    })
    public AdInfo getAd(@PathVariable("id") int id) {
        return AdInfo.of(adDao.find(id));
    }

    @PostMapping("/ad")
    @ApiOperation("Method is used to create a new ad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully inserted ad"),
            @ApiResponse(code = 400, message = "The input parameters are not valid")
    })
    public AdInfo insertAd(@ApiParam(name = "body", required = true) @Validated @RequestBody AdRequest request) {
        Ad ad = convert(request);
        adDao.insert(ad);
        return AdInfo.of(ad);
    }

    @PutMapping("/ad/{id}")
    @ApiOperation("Method is used to update an existing ad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated ad"),
            @ApiResponse(code = 400, message = "The input parameters are not valid")
    })
    public AdInfo updateAd(@ApiParam(name = "body", required = true) @Validated @RequestBody AdRequest request, @PathVariable("id") int id) {
        Ad ad = convert(request);
        ad.setId(id);
        adDao.update(ad);
        return AdInfo.of(ad);
    }

    @DeleteMapping("ad/{id}")
    @ApiOperation("Method is used to delete an existing ad")
    public void deleteAd(@PathVariable("id") int id) {
        adDao.delete(id);
    }

    private Ad convert(AdRequest request) {
        Ad ad = new Ad();
        ad.setName(request.getName());
        ad.setStatus(request.getStatus());
        try {
            ad.setAssetUrl(new URL(request.getAssetUrl()));
        } catch (MalformedURLException ignore) {
        }
        request.getCampaigns().forEach(ad::addCampaign);
        request.getPlatforms().forEach(ad::addPlatform);
        return ad;
    }
}
