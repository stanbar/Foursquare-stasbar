package com.stasbar.foursquare_stasbar.POJOs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class Flags {

    private Boolean outsideRadius;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The outsideRadius
     */
    public Boolean getOutsideRadius() {
        return outsideRadius;
    }

    /**
     *
     * @param outsideRadius
     * The outsideRadius
     */
    public void setOutsideRadius(Boolean outsideRadius) {
        this.outsideRadius = outsideRadius;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
