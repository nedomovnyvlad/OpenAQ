package com.example.vlad.openaq.ui.screen.city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vlad.openaq.R;
import com.example.vlad.openaq.entity.CityInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final Context context;

    private List<CityInfo> cityInfoList = emptyList();

    public CityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);

        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityInfo cityInfo = cityInfoList.get(position);

        holder.nameTextView.setText(cityInfo.name());

        holder.countTextView.setText(
                context.getString(R.string.item_measurements, cityInfo.count())
        );
    }

    @Override
    public int getItemCount() {
        return cityInfoList.size();
    }

    public void setData(List<CityInfo> cityInfoList) {
        this.cityInfoList = unmodifiableList(cityInfoList);
        notifyDataSetChanged();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_name)
        TextView nameTextView;

        @BindView(R.id.textview_count)
        TextView countTextView;

        CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
