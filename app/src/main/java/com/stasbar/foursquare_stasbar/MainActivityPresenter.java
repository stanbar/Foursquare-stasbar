package com.stasbar.foursquare_stasbar;

import com.stasbar.foursquare_stasbar.POJOs.Group;
import com.stasbar.foursquare_stasbar.POJOs.LatLng;
import com.stasbar.foursquare_stasbar.POJOs.VenueList;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivityView view;
    private boolean downloadingInProgress = false;

    public MainActivityPresenter(IMainActivityView view) {
        this.view = view;
    }

    @Override
    public void getVenuesList(double longitude, double latitude) {
        if (!downloadingInProgress) {
            downloadingInProgress = true;

            ApiClient.getApiClient().getVenuesForLl(Singleton.getInstance().getClientID(), Singleton.getInstance().getClientSecret()
                    , "20130815", new LatLng(latitude, longitude), "1"
                    , new Callback<VenueList>() {
                @Override
                public void success(VenueList venueList, Response response) {
                    if (venueList.getMeta().getCode() == 200) {
                        view.setList(new ArrayList<Group>(venueList.getResponse().getGroups()));
                        view.showMessage("Found best " + venueList.getResponse().getGroups().get(0).getItems().size() + " results", Style.INFO);
                    } else {
                        view.showMessage("Ups, Error code: " + venueList.getMeta().getCode(), Style.ALERT);
                    }
                    downloadingInProgress = false;
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getResponse() != null) {
                        view.showMessage("Ups, Error code: " + error.getResponse().getStatus(), Style.ALERT);
                    } else {
                        view.showMessage("No internet connection", Style.ALERT);
                    }
                    view.stopRefresh();
                    downloadingInProgress = false;
                }
            });
        }
    }

    @Override
    public void getVenuesList(double longitude, double latitude, String section) {
        if (!downloadingInProgress) {
            downloadingInProgress = true;
            ApiClient.getApiClient().getVenuesForLl(Singleton.getInstance().getClientID(), Singleton.getInstance().getClientSecret()
                    , "20130815", new LatLng(latitude, longitude), "1", section
                    , new Callback<VenueList>() {
                @Override
                public void success(VenueList venueList, Response response) {
                    if (venueList.getMeta().getCode() == 200) {
                        view.setList(new ArrayList<Group>(venueList.getResponse().getGroups()));
                        view.showMessage("Found best " + venueList.getResponse().getGroups().get(0).getItems().size() + " results", Style.INFO);
                    } else {
                        view.showMessage("Ups, Error code: " + venueList.getMeta().getCode(), Style.ALERT);
                    }
                    downloadingInProgress = false;
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getResponse() != null) {
                        view.showMessage("Ups, Error code: " + error.getResponse().getStatus(), Style.ALERT);
                    } else {
                        view.showMessage("No internet connection", Style.ALERT);
                    }
                    view.stopRefresh();
                    downloadingInProgress = false;
                }
            });
        }
    }
}
