package ua.com.hedgehog.adspublisher.model;

import java.net.URL;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Ad")
public class Ad extends AdRequest {
    @ApiModelProperty("Ad id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
