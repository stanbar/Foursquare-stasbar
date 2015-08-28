package com.stasbar.foursquare_stasbar;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.animators.internal.ViewHelper;
import com.stasbar.foursquare_stasbar.POJOs.Group;
import com.stasbar.foursquare_stasbar.POJOs.Item;

import java.util.ArrayList;

/**
 * Created by Stanisław on 27.08.2015.
 */
public class UltimateRecyclerAdapter extends UltimateViewAdapter<RecyclerView.ViewHolder> {
    private ArrayList<Group> groupList = new ArrayList<>();
    private ArrayList<Item> venueList;
    public Context context;
    private LayoutInflater inflater;
    public int thumbnailSize = 600;
    private int screenWidth;
    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = 5;
    private boolean isFirstOnly = true;

    public UltimateRecyclerAdapter(Context context, ArrayList<Group> groupList, int screenWidth) {
        this.context = context;
        this.screenWidth = screenWidth;
        inflater = LayoutInflater.from(context);
        this.groupList = groupList;
        this.venueList = new ArrayList<>(groupList.get(0).getItems());
        notifyItemRangeChanged(0, venueList.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (venueList.get(position).getVenue().getName() != null
                && venueList.get(position).getVenue().getStats().getCheckinsCount() != null
                && venueList.get(position).getVenue().getLocation().getAddress() != null && venueList.get(position).getVenue().getLocation().getDistance() != null) {

            ((ViewHolder) holder).tvName.setText(venueList.get(position).getVenue().getName());
            ((ViewHolder) holder).tvCheckInCount.setText(String.valueOf(venueList.get(position).getVenue().getStats().getCheckinsCount()));
            ((ViewHolder) holder).tvAddress.setText(venueList.get(position).getVenue().getLocation().getAddress());
            ((ViewHolder) holder).tvDistance.setText(String.valueOf(venueList.get(position).getVenue().getLocation().getDistance()));
        }else{
            ((ViewHolder) holder).tvName.setText("unknown");
            ((ViewHolder) holder).tvCheckInCount.setText("unknown");
            ((ViewHolder) holder).tvAddress.setText("unknown");
            ((ViewHolder) holder).tvDistance.setText("unknown");
        }
        String prefix = " ";
        String sufix = " ";
        if (venueList.get(position).getVenue().getPhotos().getGroups() != null) {
            if (venueList.get(position).getVenue().getPhotos().getGroups().size() != 0) {
                if (venueList.get(position).getVenue().getPhotos().getGroups().get(0).getItems() != null) {
                    if (venueList.get(position).getVenue().getPhotos().getGroups().get(0).getItems().size() != 0) {
                        prefix = venueList.get(position).getVenue().getPhotos().getGroups().get(0).getItems().get(0).getPrefix() + "width" + screenWidth + "/";
                        sufix = venueList.get(position).getVenue().getPhotos().getGroups().get(0).getItems().get(0).getSuffix().substring(1);
                    }
                }
            }
        }
        Glide.with(context)
                .load(prefix + sufix)
                .error(R.drawable.error)
                .override(screenWidth, (int) (screenWidth / 1.618f))
                .centerCrop()
                .into(((ViewHolder) holder).ivVenuePhoto);
        if (!isFirstOnly || position > mLastPosition) {
            for (Animator anim : getAdapterAnimations(holder.itemView, AdapterAnimationType.ScaleIn)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);
            }
            mLastPosition = position;
        } else {
            ViewHelper.clear(holder.itemView);
        }

    }

    @Override
    public int getAdapterItemCount() {
        return venueList.size();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new UltimateRecyclerviewViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venue_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void insert(Item item, int position) {
        insert(venueList, item, position);
    }

    public void remove(int position) {
        remove(venueList, position);
        notifyItemRemoved(position);
    }

    public void clear() {
        clear(venueList);

    }

    public void clearBoth() {
        clear(venueList);
        clear(groupList);
    }

    @Override
    public void toggleSelection(int pos) {
        super.toggleSelection(pos);
    }

    @Override
    public void setSelected(int pos) {
        super.setSelected(pos);
    }

    @Override
    public long generateHeaderId(int i) {
        if (getItem(i).getVenue().getName().length() > 0)
            return getItem(i).getVenue().getName().charAt(0);
        else return -1;
    }

    @Override
    public void clearSelection(int pos) {
        super.clearSelection(pos);
    }


    public void swapPositions(int from, int to) {
        swapPositions(venueList, from, to);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.venue_row, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    public class ViewHolder extends UltimateRecyclerviewViewHolder {
        TextView tvName;
        TextView tvCheckInCount;
        TextView tvAddress;
        TextView tvDistance;
        ImageView ivVenuePhoto;
        LinearLayout layoutDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutDetails = (LinearLayout) itemView.findViewById(R.id.layout_details);
            tvName = (TextView) itemView.findViewById(R.id.text_view_name);
            tvAddress = (TextView) itemView.findViewById(R.id.text_view_address);
            tvDistance = (TextView) itemView.findViewById(R.id.text_view_distance);
            tvDistance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double lat = venueList.get(getAdapterPosition()).getVenue().getLocation().getLat();
                    double lng = venueList.get(getAdapterPosition()).getVenue().getLocation().getLng();
                    String label = venueList.get(getAdapterPosition()).getVenue().getName();
                    Uri gmmIntentUri = Uri.parse("geo:0.0?q=" + lat + "," + lng + "(" + label + ")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(mapIntent);
                    }
                }
            });
            tvCheckInCount = (TextView) itemView.findViewById(R.id.text_view_count);
            ivVenuePhoto = (ImageView) itemView.findViewById(R.id.image_view_venue_photo);
            ivVenuePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (layoutDetails.getVisibility() == View.GONE) {
                        layoutDetails.setVisibility(View.VISIBLE);
                    } else {
                        layoutDetails.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public Item getItem(int position) {
        if (customHeaderView != null)
            position--;
        if (position < venueList.size())
            return venueList.get(position);
        else return null;
    }

}
