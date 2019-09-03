package com.example.submissionempat.ui.main.adapter.all;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submissionempat.R;
import com.example.submissionempat.model.MovieData;
import com.example.submissionempat.model.TvData;
import com.example.submissionempat.ui.detail.DetailActivity;

import java.util.ArrayList;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.ViewHolder> {

    private ArrayList<TvData> mData;
    private final Context context;

    public ListTvShowAdapter(Context context) {
        this.mData = new ArrayList<>();
        this.context = context;
    }

    public void setTvData(ArrayList<TvData> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListTvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_data, viewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTvShowAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(TvData tvData){
        this.mData.add(tvData);
        notifyItemInserted(mData.size()-1);
    }

    public void removeItem(int position){
        this.mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mData.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView imgPhoto;
        Button btnReadMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvDesc = itemView.findViewById(R.id.longdesc_item);
            btnReadMore = itemView.findViewById(R.id.btn_read_more);
        }

        void bind(final TvData tvData){
            tvName.setText(tvData.getName());
            tvDesc.setText(tvData.getOverview());

            Glide.with(itemView.getContext())
                    .load(tvData.getPoster_path())
                    .into(imgPhoto);

            btnReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieData list = new MovieData();
                    list.setTitle(tvData.getName());
                    list.setOverview(tvData.getOverview());
                    list.setPoster_path(tvData.getPoster_path());

                    Intent sendData = new Intent(context, DetailActivity.class);
                    sendData.putExtra(DetailActivity.EXTRA_DATA,list);
                    context.startActivity(sendData);
                }
            });
        }
    }
}
