package com.superpoginimikel.simpleweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.superpoginimikel.simpleweather.databinding.CityRecyclerviewItemBinding;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private final LayoutInflater mInflater;
    private List<CityEntity> cities; // Cached copy of words

    @Nullable
    private final CityClickCallback cityClickCallback;

    public CityListAdapter(Context context, CityClickCallback cityClickCallback) {
        mInflater = LayoutInflater.from(context);
        this.cityClickCallback = cityClickCallback;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityRecyclerviewItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.city_recyclerview_item,
                        parent, false);
        binding.setCallback(cityClickCallback);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityEntity current = cities.get(position);
        holder.binding.setCityEntity(current);
        holder.binding.executePendingBindings();
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cities != null)
            return cities.size();
        else return 0;
    }

    public CityEntity getWordAtPosition(int position) {
        return cities.get(position);
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        final CityRecyclerviewItemBinding binding;

        CityViewHolder(CityRecyclerviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
