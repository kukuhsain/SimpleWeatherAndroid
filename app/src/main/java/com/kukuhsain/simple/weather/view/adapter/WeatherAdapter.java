package com.kukuhsain.simple.weather.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.pojo.Weather;
import com.kukuhsain.simple.weather.view.WeatherListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 14/11/16.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private List<Weather> weathers;

    public WeatherAdapter(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(context, weathers.get(position));
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name) TextView tvName;
        @BindView(R.id.tv_description) TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Context context, Weather weather) {
            tvName.setText(weather.getMain());
            tvDescription.setText(weather.getDescription());
            itemView.setOnClickListener(view -> {
                ((WeatherListActivity) context).onItemClicked(weather);
            });
        }
    }
}
