package com.stasbar.foursquare_stasbar;

import com.stasbar.foursquare_stasbar.POJOs.LatLng;
import com.stasbar.foursquare_stasbar.POJOs.VenueList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by Stanisław on 27.08.2015.
 */
public interface ApiService {

    @GET("/venues/explore")
    void getVenuesForLl(@Query("client_id") String clientID,@Query("client_secret") String clientSecret
            ,@Query("v") String version,@Query("ll") LatLng coordinates
            ,@Query("venuePhotos") String photos,Callback<VenueList> callback);

    @GET("/venues/explore")
    void getVenuesForLl(@Query("client_id") String clientID,@Query("client_secret") String clientSecret
            ,@Query("v") String version,@Query("ll") LatLng coordinates
            ,@Query("venuePhotos") String photos,@Query("section") String section,Callback<VenueList> callback);


}
