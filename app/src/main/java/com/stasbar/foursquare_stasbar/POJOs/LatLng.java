package com.stasbar.foursquare_stasbar.POJOs;

import java.util.Locale;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override public String toString() {
        return String.format(Locale.ENGLISH,"%.1f,%.1f", lat, lng);
    }
}
