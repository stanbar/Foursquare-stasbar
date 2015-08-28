package com.stasbar.foursquare_stasbar;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class ApiClient {
    private static ApiService apiService;

    public static ApiService getApiClient(){
        if(apiService==null){
            OkClient client = new OkClient();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(client)
                    .setEndpoint("https://api.foursquare.com/v2")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            apiService = restAdapter.create(ApiService.class);
        }
        return apiService;
    }
}
