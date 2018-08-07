package android.attendancecalculator;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by The Dark Knight on 27-02-2016.
 */
public class ViewAttendance extends Fragment {
    View rootView;
    TextView databaseText,attendanceText;
    DatabaseHandler databaseHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_view_attendance,container,false);

        databaseText = (TextView)rootView.findViewById(R.id.databaseText);
        attendanceText = (TextView)rootView.findViewById(R.id.attendanceText);

      databaseHandler = new DatabaseHandler(getActivity(),null,null,1);


        printDatabase();
        showAttendance();



        return rootView;
    }

    public void printDatabase(){
        String printString = databaseHandler.databaseToString();
        databaseText.setText(printString);
        databaseText.setMovementMethod(new ScrollingMovementMethod());

    }

    public void showAttendance(){

        double a = databaseHandler.calculateAttendance();
        DecimalFormat df = new DecimalFormat("#.00");

        attendanceText.setText("Attendance is " + df.format(a) + "%");
    }
}
