package layout;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import karthik.lifecycle.MainActivity;
import karthik.lifecycle.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link controlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class controlFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    Button start;
    Button lap;
    Button reset;
    Button change;
    TextView setTime;
    int min = 0;
    int sec = 0;
    int hour = 0;
    boolean running = false;
    timer tim = new timer();


    public controlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_control, container, false);
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        start = (Button) view.findViewById(R.id.startbtn);
        lap = (Button) view.findViewById(R.id.lapbtn);
        reset = (Button) view.findViewById(R.id.rstbtn);
        change = (Button) view.findViewById(R.id.changeFragBtn);
        setTime = (TextView) view.findViewById(R.id.currTime);
        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);
        change.setOnClickListener(this);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            change.setVisibility(View.INVISIBLE);
        }
            return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        tim.execute("");
        setRetainInstance(true);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == start.getId()){
            mListener.onButtonClicked(0);
            toggleTimer();

        }
        if(v.getId() == lap.getId()){
            mListener.onButtonClicked(1);
        }
        if(v.getId() == reset.getId()){
            mListener.onButtonClicked(2);
            resetClk();
        }
        if(v.getId() == change.getId()){
            mListener.onButtonClicked(3);
        }
    }

        public void toggleTimer() {
        if(!running){
            startWatch();
            start.setText("PAUSE");
        }
        else if(running){
            //try{
                stopWatch(); //start the task
                start.setText("START");
           // }
         //   catch(Exception e1){
        //        Toast.makeText(MainActivity.this, "Error starting: "+e1, Toast.LENGTH_LONG).show();
         //   }
        }
    }


    public void startWatch(){
        running = true;
    }
    public void stopWatch(){
        running= false;
    }

    public void resetClk(){
        min = 0;
        sec = 0;
        hour = 0;
        setTime.setText(String.format("%02d:%02d:%02d", hour, min, sec));
        running = false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onButtonClicked(int infoID);
    }


    public class timer extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            while (true) {
                while (sec < 60 && running) {
                    running = true;
                    sec++;
                    if (sec == 60) {
                        sec = 0;
                        min++;
                    }
                    if (min == 60) {
                        hour++;
                    }
                    String output;
                    output = String.format("%02d:%02d:%02d", hour, min, sec);
                    this.publishProgress(output);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }


                }

            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            TextView display = setTime;
            display.setText(values[0]);
        }

    }


}
