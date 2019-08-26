package com.example.submissionempat.ui.main.adapter;

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
import com.example.submissionempat.ui.detail.DetailActivity;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    private ArrayList<MovieData> mData;
    private final Context context;

    public ListMovieAdapter(Context context) {
        this.context = context;
        mData = new ArrayList<>();
    }

    public void setMovieData(ArrayList<MovieData> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_data, viewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
        void bind(final MovieData movieData){
            tvName.setText(movieData.getTitle());
            tvDesc.setText(movieData.getOverview());

            Glide.with(itemView.getContext())
                    .load(movieData.getPoster_path())
                    .into(imgPhoto);

            btnReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieData list = new MovieData();
                    list.setTitle(movieData.getTitle());
                    list.setOverview(movieData.getOverview());
                    list.setPoster_path(movieData.getPoster_path());

                    Intent sendData = new Intent(context, DetailActivity.class);
                    sendData.putExtra(DetailActivity.EXTRA_DATA,list);
                    context.startActivity(sendData);
                }
            });
        }
    }
}
