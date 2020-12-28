package com.son.getlistapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.son.getlistapi.Model.Detail;
import com.son.getlistapi.R;

import java.util.List;

public class GetListAdapter extends RecyclerView.Adapter<GetListAdapter.ViewHolder> {
    private List<Detail> detailList;
    Context context;

    public GetListAdapter(List<Detail> detailList, Context context) {
        this.detailList = detailList;
        this.context = context;
    }

    @NonNull
    @Override
    public GetListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetListAdapter.ViewHolder holder, int position) {
        holder.txtFullName.setText("FullName: "+detailList.get(position).getFullName());
        holder.txtName.setText("Name: "+detailList.get(position).getName());
        Glide.with(context).load(detailList.get(position).getOwner().getAvatarUrl()).into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
    public void updateData(List<Detail> list){
        detailList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtName, txtFullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtName = itemView.findViewById(R.id.txtName);
            txtFullName = itemView.findViewById(R.id.txtFullName);
        }
    }
}
