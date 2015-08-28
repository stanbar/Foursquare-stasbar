package com.stasbar.foursquare_stasbar;

import com.stasbar.foursquare_stasbar.POJOs.Group;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Stanisław on 27.08.2015.
 */
public interface IMainActivityView {
    void setList(ArrayList<Group> groupList);
    void showMessage(String string,Style style);
    void stopRefresh();
}
