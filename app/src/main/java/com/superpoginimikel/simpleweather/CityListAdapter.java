package com.superpoginimikel.simpleweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private final LayoutInflater mInflater;
    private List<CityEntity> cities; // Cached copy of words

    public CityListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.city_recyclerview_item, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        if (cities != null) {
            CityEntity current = cities.get(position);
            holder.cityItemView.setText(current.getCity());
        } else {
             holder.cityItemView.setText(R.string.city_not_found);
        }
    }

    /**
     *     Associate a list of words with this adapter
     */

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (cities != null)
            return cities.size();
        else return 0;
    }

    /**
     * Get the word at a given position.
     * This method is useful for identifying which word
     * was clicked or swiped in methods that handle user events.
     *
     * @param position
     * @return The word at the given position
     */
    public CityEntity getWordAtPosition(int position) {
        return cities.get(position);
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private final TextView cityItemView;

        private CityViewHolder(View itemView) {
            super(itemView);
            cityItemView = itemView.findViewById(R.id.textView);
        }
    }
}
