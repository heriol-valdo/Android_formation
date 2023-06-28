package com.example.tpf2i;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CaractereAdapter extends RecyclerView.Adapter<CaractereAdapter.PersonnageViewHolder>{

    private Context context;
    private List<Caractere> caractereList;

    public CaractereAdapter(Context context, List<Caractere> caractereList) {
        this.context = context;
        this.caractereList = caractereList;
    }

    @NonNull
    @Override
    public PersonnageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personnage_item,parent,false);
        return new PersonnageViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonnageViewHolder holder, int position) {
        Caractere caractere = caractereList.get(position);
        holder.txtName.setText(caractere.getName());

        Glide.with(context)
                .load(caractere.getImageUrl())
                .placeholder(R.drawable.film_loading)
                .centerCrop()
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return caractereList.size();
    }

    public class PersonnageViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgView;
        private TextView txtName;

        public PersonnageViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.image_thumbnail_path);
            txtName = itemView.findViewById(R.id.name);
        }
    }
}
