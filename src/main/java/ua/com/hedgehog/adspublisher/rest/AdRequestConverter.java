package ua.com.hedgehog.adspublisher.rest;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.core.convert.converter.Converter;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.AdRequest;

public class AdRequestConverter implements Converter<AdRequest, Ad> {
    @Override
    public Ad convert(AdRequest request) {
        Ad ad = new Ad();
        ad.setName(request.getName());
        ad.setStatus(request.getStatus());
        ad.setAssetUrl(request.getAssetUrl());
        request.getCampaigns().forEach(ad::addCampaign);
        request.getPlatforms().forEach(ad::addPlatform);
        return ad;
    }
}
