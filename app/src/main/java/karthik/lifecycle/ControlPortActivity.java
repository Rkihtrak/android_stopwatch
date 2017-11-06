package karthik.lifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ControlPortActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.fragment_list);

        Intent intent = getIntent();
        String info = intent.getStringExtra("ListofLaps");
        TextView textView = (TextView) findViewById(R.id.timesList);
        textView.setText(info);
    }

    public void goBack(View v){
        finish();
        return;
    }

//    @Override
//    public void onClick(View v) {
//        finish();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}
