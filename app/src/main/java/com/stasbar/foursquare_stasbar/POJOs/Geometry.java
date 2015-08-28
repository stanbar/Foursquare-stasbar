package com.stasbar.foursquare_stasbar.POJOs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class Geometry {

    private Bounds bounds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     *
     * @param bounds
     * The bounds
     */
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
