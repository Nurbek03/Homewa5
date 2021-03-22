package com.example.myapplicationrecycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TwoFragment extends Fragment implements MasegSender, ModelSender{

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    List<ContactModel> list;
    Context context;

    public void add(Context context, ContactModel contactModel) {
        this.context = context;
        list.add(contactModel);
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        mainAdapter = new MainAdapter(list, getContext());
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void getMeseg(String msg) {
        add(getContext(),new ContactModel(msg, "", ""));

    }


    @Override
    public void modelSender(ContactModel contactModel) {
        add(getContext(),contactModel);

    }
}

