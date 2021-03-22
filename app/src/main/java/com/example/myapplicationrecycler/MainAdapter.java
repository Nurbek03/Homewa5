package com.example.myapplicationrecycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    //public static List<?> list;
    public List<ContactModel> list;
    public Context context;

    ItemClickListener listener;
    private ContactModel model;

    public MainAdapter(List<ContactModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void add(Context context, ContactModel contactModel) {
        this.context = context;
        list.add(contactModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

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

    public void setonClickListener(ItemClickListener mListener) {
        this.listener = mListener;
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtView;
        private TextView phoneNumber;
        private ImageView imageUser;
        ContactModel model;
        Button btnAdd;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtView = itemView.findViewById(R.id.txtView);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            imageUser = itemView.findViewById(R.id.imageView);
            btnAdd = itemView.findViewById(R.id.btn);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(v);
                }
            });
        }

        private void showPopupMenu(View v) {
            PopupMenu popupMenu = new PopupMenu(itemView.getContext(), v);
            popupMenu.inflate(R.menu.menu);

            popupMenu
                    .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item1:
                                    Toast.makeText(context.getApplicationContext(),
                                            "Вы выбрали PopupMenu 1",
                                            Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.item2:
                                    Toast.makeText(context.getApplicationContext(),
                                            "Вы выбрали PopupMenu 2",
                                            Toast.LENGTH_SHORT).show();
                                    list.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                    return true;
                                case R.id.item3:
                                    Toast.makeText(context.getApplicationContext(),
                                            "Вы выбрали PopupMenu 3",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Intent.ACTION_VIEW,
                                            Uri.fromParts("sms","0706450465",null));
                                    intent.putExtra("sms_body","Hello , how are you");
                                    itemView.getContext().startActivity(intent);
                                    return true;
                                default:
                                    return false;

                            }
                        }
                    });

            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                @Override
                public void onDismiss(PopupMenu menu) {
                    Toast.makeText(context.getApplicationContext(), "onDismiss",
                            Toast.LENGTH_SHORT).show();
                }
            });
            popupMenu.show();
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
            if (listener != null) {
                listener.onItemClick(getAdapterPosition());
            }
        }

    }
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}