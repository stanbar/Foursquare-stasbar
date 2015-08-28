package com.stasbar.foursquare_stasbar;

import android.util.Pair;

/**
 * Created by Stanisław on 27.08.2015.
 */
public interface IMainActivityPresenter {
    void getVenuesList(double longitude, double latitude);
    void getVenuesList(double longitude, double latitude,String section);
}
