package com.stasbar.foursquare_stasbar.POJOs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class Bounds {

    private Ne ne;
    private Sw sw;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ne
     */
    public Ne getNe() {
        return ne;
    }

    /**
     *
     * @param ne
     * The ne
     */
    public void setNe(Ne ne) {
        this.ne = ne;
    }

    /**
     *
     * @return
     * The sw
     */
    public Sw getSw() {
        return sw;
    }

    /**
     *
     * @param sw
     * The sw
     */
    public void setSw(Sw sw) {
        this.sw = sw;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
