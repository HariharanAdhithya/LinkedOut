package com.unagi.hackaz.linkedout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.unagi.hackaz.linkedout.Model.Object;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bhargav on 1/13/18.
 */

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.CompViewHolder> {

    MainActivity activity;

    public CompanyListAdapter(ArrayList<Object> companies, Context context) {
        this.companies = companies;
        activity = (MainActivity) context;
    }

    ArrayList<Object> companies = new ArrayList<>();


    @Override
    public CompanyListAdapter.CompViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        View view = inflater.inflate(R.layout.item_company,parent,false);

        CompViewHolder compViewHolder = new CompViewHolder(view);

        return compViewHolder;
    }

    @Override
    public void onBindViewHolder(CompanyListAdapter.CompViewHolder holder, int position) {

        Object company = companies.get(position);


        holder.compTitle.setText(company.getName());
        Picasso.with(holder.compImage.getContext()).load(R.drawable.placeholder).into(holder.compImage);


    }

    @Override
    public int getItemCount() {
        return companies.size();
    }


    public void setAdapter(ArrayList<Object> comp){

        companies = comp;
        notifyDataSetChanged();

    }


    public class CompViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView compTitle;
        CircleImageView compImage;

        public CompViewHolder(View itemView) {
            super(itemView);

            compTitle = (TextView) itemView.findViewById(R.id.tv_comp_title);
            compImage = (CircleImageView) itemView.findViewById(R.id.iv_comp_img);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Object comp = companies.get(pos);

            Intent in = new Intent(activity,TextActivity.class);
            in.putExtra("compName",comp.getName());
            activity.startActivity(in);
        }
    }
}
