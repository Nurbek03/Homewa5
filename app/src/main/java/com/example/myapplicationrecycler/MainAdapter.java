package com.example.myapplicationrecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public List<ContactModel> list;
    public Context context;

    ItemClickListener listener;

    public MainAdapter(List<ContactModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent,false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
           holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtView;
        private TextView phoneNumber;
        private ImageView imageUser;
        ContactModel model;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtView = itemView.findViewById(R.id.txtView);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            imageUser = itemView.findViewById(R.id.imageView);
        }

        public void onBind(ContactModel model) {
            this.model = model;
            txtView.setText(model.getName());
            phoneNumber.setText(model.getPhone());

            if (model.getImage() != null) {
                Glide.with(context)
                        .load(model.getImage())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageUser);
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onItemClick(getAdapterPosition());
            }
        }
    }

    public void setOnClickListener(ItemClickListener mListener){
        this.listener = mListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}