package com.margsapp.iosdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openDialog(View view){
        iOSDialog.Builder
                .with(MainActivity.this)
                .setTitle("Title")
                .setMessage("This is the description and this is how it looks.")
                .setPositiveText("Yes")
                .setPostiveTextColor(getApplicationContext().getResources().getColor(R.color.red))
                .setNegativeText("Cancel")
                .setNegativeTextColor(getApplicationContext().getResources().getColor(R.color.company_blue))
                .onPositiveClicked(new iOSDialogListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        //Nothing happens
                    }
                })
                .onNegativeClicked(new iOSDialogListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        //Do Nothing
                    }
                })
                .isCancellable(true)
                .build()
                .show();
    }
}