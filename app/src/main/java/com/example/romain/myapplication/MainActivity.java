package com.example.romain.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.romain.myapplication.questions.QuestionsActivity;


public class MainActivity extends AppCompatActivity {

    TextView myTextView;
    SeekBar actionSeekBar;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) this.findViewById(R.id.numberView);
        actionSeekBar = (SeekBar) this.findViewById(R.id.seekBar);
        actionSeekBar.setMax(30);
        myButton = (Button) this.findViewById(R.id.buttonView);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, QuestionsActivity.class);
                myIntent.putExtra("SeekValue", actionSeekBar.getProgress());
                startActivity(myIntent);
            }
        });
        actionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myTextView.setText(Float.toString(progress).replace(".0", ""));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
