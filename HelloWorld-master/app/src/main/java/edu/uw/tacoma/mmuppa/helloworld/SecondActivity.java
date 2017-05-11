package edu.uw.tacoma.mmuppa.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String msg = getIntent().getStringExtra(MainActivity.MESSAGE);
        TextView msgText = (TextView) findViewById(R.id.txt_sent);
        msgText.setText(msg);

    }
}
