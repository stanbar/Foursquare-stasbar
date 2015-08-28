package com.stasbar.foursquare_stasbar;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.marshalchen.ultimaterecyclerview.ObservableScrollState;
import com.marshalchen.ultimaterecyclerview.ObservableScrollViewCallbacks;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.stasbar.foursquare_stasbar.POJOs.Group;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    @Bind(R.id.recycler_view_venues) UltimateRecyclerView ultimateRecyclerView;
    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Bind(R.id.header)
    LinearLayout header;
    private IMainActivityPresenter presenter;
    private UltimateRecyclerAdapter adapter;
    double longitude;
    double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new MainActivityPresenter(this);
        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ultimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getVenuesList(longitude, latitude);
            }
        });
        ultimateRecyclerView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            }

            @Override
            public void onDownMotionEvent() {
            }

            @Override
            public void onUpOrCancelMotionEvent(ObservableScrollState observableScrollState) {
                if (observableScrollState == ObservableScrollState.DOWN) {
                    ultimateRecyclerView.showView(header, ultimateRecyclerView, getScreen().second);
                } else if (observableScrollState == ObservableScrollState.UP) {
                    ultimateRecyclerView.hideView(header, ultimateRecyclerView, getScreen().second);
                } else if (observableScrollState == ObservableScrollState.STOP) {
                }
            }
        });
        // Location
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            presenter.getVenuesList(longitude,latitude);
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600, 1000, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                presenter.getVenuesList(longitude,latitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setList(ArrayList<Group> groupList) {
        //adapter = new CustomRecyclerAdapter(getActivity(), list,thumbnailSize);
        adapter = new UltimateRecyclerAdapter(this,groupList,getScreen().first);
        ultimateRecyclerView.setAdapter(adapter);
        stopRefresh();
    }

    @Override
    public void showMessage(String string, Style style) {
        Crouton.makeText(this,string,style).show();
    }

    @Override
    public void stopRefresh() {
        ultimateRecyclerView.setRefreshing(false);
    }

    public Pair<Integer, Integer> getScreen(){
        int measuredWidth = 0;
        int measuredHeigth = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
            w.getDefaultDisplay().getSize(size);
            measuredWidth = size.x;
            measuredHeigth = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            measuredWidth = d.getWidth();
            measuredHeigth = d.getHeight();
        }
        return new Pair<>(measuredWidth,measuredHeigth);
    }

    public void changeSection(View view) {
        String section = "";
        switch(view.getId()){
            case R.id.layout_top:
                section = "topPicks";
                break;
            case R.id.layout_food:
                section = "food";
                break;
            case R.id.layout_drink:
                section = "drink";
                break;
            case R.id.layout_coffe:
                section = "coffe";
                break;
        }
        presenter.getVenuesList(longitude,latitude,section);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
