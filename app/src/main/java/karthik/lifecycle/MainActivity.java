package karthik.lifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import layout.controlFragment;
import layout.listFragment;

public class MainActivity extends AppCompatActivity implements listFragment.OnFragmentInteractionListener, controlFragment.OnFragmentInteractionListener {


    int sec;
    int min;
    int hour;
  //  timer tim;
    String CURRENTGLOBAL = "00:00:00";
    boolean running = true;
    timeClass storeTimes;// = new timeClass();
    String SAVEDLAPSGLOBAL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   tim = new timer();
        storeTimes = new timeClass();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String restored = "";

        SAVEDLAPSGLOBAL = savedInstanceState.getString("SAVEDLAPS");
        storeTimes.clearList();
        storeTimes.pushTime(SAVEDLAPSGLOBAL);



        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {


            TextView laps = (TextView) findViewById(R.id.timesList);
            laps.setText(SAVEDLAPSGLOBAL);

        }


            CURRENTGLOBAL = savedInstanceState.getString("CURRTIME");
            TextView preserve = (TextView)findViewById(R.id.currTime);
            preserve.setText(CURRENTGLOBAL);

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        outState.putString("SAVEDLAPS", storeTimes.writeTime());

        TextView preserve = (TextView)findViewById(R.id.currTime);
        outState.putString("CURRTIME", preserve.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onButtonClicked(int infoID) {

        listFragment lstFrag =
                (listFragment) getSupportFragmentManager().findFragmentById(R.id.timeslistFrag);
        controlFragment ctFrag =
                (controlFragment) getSupportFragmentManager().findFragmentById(R.id.ctrlFrag);

        if(infoID == 1){
//            storeTimes.setCurrent(((TextView)findViewById(R.id.currTime)).getText().toString());
            String push = ((TextView)findViewById(R.id.currTime)).getText().toString();
            storeTimes.pushTime(push);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                TextView newLap = (TextView)findViewById(R.id.timesList);
                newLap.setText(storeTimes.writeTime());
            }
//            TextView newLap = (TextView)findViewById(R.id.timesList);
//            newLap.setText(storeTimes.writeTime());

        }

        if (infoID == 3) {
            //if (ctFrag != null && ctFrag.isInLayout()) {

                Intent intent = new Intent(this, ControlPortActivity.class);
                intent.putExtra("ListofLaps", storeTimes.writeTime());
                startActivity(intent);
           // }
        }

    }

//    public class timer extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            while (true) {
//                while (sec < 60 && running) {
//                    running = true;
//                    sec++;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (Exception e1) {
//                        System.out.println(e1);
//                    }
//                    if (sec == 60) {
//                        sec = 0;
//                        min++;
//                    }
//                    if (min == 60) {
//                        hour++;
//                    }
//                    String output;
//                    output = String.format("%02d:%02d:%02d", hour, min, sec);
//                    this.publishProgress(output);
//
//
//                }
//
//            }
//
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            TextView display = (TextView) findViewById(R.id.currTime);
//            display.setText(values[0]);
//        }
//
//    }
}

