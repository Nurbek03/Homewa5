package com.example.myapplicationrecycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.ImsMmTelManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<ContactModel> list;
    private Button btnAdd;

    protected static String KEY = "kry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(list, this);
        recyclerView.setAdapter(adapter);
        btnAdd = findViewById(R.id.btnAdd);
        adapter.setOnClickListener(this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApplicationActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            ContactModel model = (ContactModel) data.getSerializableExtra(KEY);
            list.add(model);
            adapter.notifyDataSetChanged();
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
            | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();
            Collections.swap(adapter.list, positionDrag, positionTarget);
            adapter.notifyItemMoved(positionDrag, positionTarget);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            adapter.list.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };


    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
    }
}