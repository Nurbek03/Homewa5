package com.example.myapplicationrecycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FirstFragment.onClickListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private TwoFragment twoFragment;
    protected static String KEY = "kry";
    public MasegSender masegSender;
    private ModelSender modelSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDynamicFragment();
        twoFragment = (TwoFragment) getSupportFragmentManager().findFragmentById(R.id.secondFragmentContainer);
        masegSender = twoFragment;
        modelSender = twoFragment;

    }

    private void setDynamicFragment() {
        ThreeFragment threeFragment = new ThreeFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainer, threeFragment);
        transaction.commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            assert data != null;
            ContactModel model = (ContactModel) data.getSerializableExtra(KEY);
            modelSender.modelSender(model);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onclick() {
        Intent intent = new Intent(MainActivity.this, ApplicationActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void hide() {
        ConstraintLayout layoutOfSecondFragment = findViewById(R.id.constrainOfSecondFragment);
        layoutOfSecondFragment.setVisibility(View.GONE);
        TwoFragment secondFragment = (TwoFragment) fragmentManager.findFragmentById(R.id.secondFragmentContainer);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.hide(secondFragment);
        transaction.commit();
    }

    @Override
    public void show() {
        ConstraintLayout layoutOfSecondFragment = findViewById(R.id.constrainOfSecondFragment);
        layoutOfSecondFragment.setVisibility(View.VISIBLE);
        TwoFragment secondFragment = (TwoFragment) fragmentManager.findFragmentById(R.id.secondFragmentContainer);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.show(secondFragment);
        transaction.commit();
    }

    @Override
    public void transfer(String msg) {
        masegSender.getMeseg(msg);

    }
}