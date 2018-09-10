package com.example.x.code00_speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_SPEECH = 100;
    private static final String TAG = "MainActivity: ";

    private TextView textTextView;
    private Button recognizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        textTextView = new TextView(this);
        textTextView.setTextSize(26);
        recognizeButton = new Button(this);

        recognizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //effective
                    startActivityForResult(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), RESULT_SPEECH);
                } catch (ActivityNotFoundException a) {
                    Log.e(TAG, "ActivityNotFoundException");
                }
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;

        linearLayout.addView(textTextView, layoutParams);
        linearLayout.addView(recognizeButton, layoutParams);

        setContentView(linearLayout);
    }

    //effective
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SPEECH) {
            if (resultCode == RESULT_OK && null != data) {

                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                for (int i = 0; i < text.size(); i++) {
                    if (text.get(i).toUpperCase().equals("MOHAMED"))
                        Toast.makeText(MainActivity.this, "some one say your name", Toast.LENGTH_LONG).show();
                    textTextView.append(text.get(i) + " ");
                }
            }
        }
    }
}
