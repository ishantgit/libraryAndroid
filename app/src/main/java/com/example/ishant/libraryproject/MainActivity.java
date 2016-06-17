package com.example.ishant.libraryproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dump.UnfoldSDK;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UnfoldSDK.init(this,"kdfjd");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        TextView titleTextView = (TextView)toolbar.findViewById(R.id.toolbar_title);
        titleTextView.setText("Main Activity");

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        final Activity activity = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity,SecondActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onResume() {
        UnfoldSDK.init(this,"key");
        super.onResume();
    }

    @Override
    protected void onPause() {
        UnfoldSDK.dumpHierarchy(this);
        super.onPause();
    }

}
