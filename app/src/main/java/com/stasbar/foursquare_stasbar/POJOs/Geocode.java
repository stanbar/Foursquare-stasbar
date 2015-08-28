package com.stasbar.foursquare_stasbar.POJOs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class Geocode {

    private String what;
    private String where;
    private Center center;
    private String displayString;
    private String cc;
    private Geometry geometry;
    private String slug;
    private String longId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The what
     */
    public String getWhat() {
        return what;
    }

    /**
     *
     * @param what
     * The what
     */
    public void setWhat(String what) {
        this.what = what;
    }

    /**
     *
     * @return
     * The where
     */
    public String getWhere() {
        return where;
    }

    /**
     *
     * @param where
     * The where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     *
     * @return
     * The center
     */
    public Center getCenter() {
        return center;
    }

    /**
     *
     * @param center
     * The center
     */
    public void setCenter(Center center) {
        this.center = center;
    }

    /**
     *
     * @return
     * The displayString
     */
    public String getDisplayString() {
        return displayString;
    }

    /**
     *
     * @param displayString
     * The displayString
     */
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    /**
     *
     * @return
     * The cc
     */
    public String getCc() {
        return cc;
    }

    /**
     *
     * @param cc
     * The cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     *
     * @return
     * The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The longId
     */
    public String getLongId() {
        return longId;
    }

    /**
     *
     * @param longId
     * The longId
     */
    public void setLongId(String longId) {
        this.longId = longId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
