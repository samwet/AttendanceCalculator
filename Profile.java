package android.attendancecalculator;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by The Dark Knight on 27-02-2016.
 */
public class Profile extends Fragment {
    View rootView;
    EditText studentName;
    Button button;
    TextView showStudentName,textView;
    SharedPreferences prefVar;
    ImageView imageProfile;

    public static final String key = "Nil";

    public static int counter =0;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile,container,false);


        button=(Button)rootView.findViewById(R.id.button);
        studentName=(EditText)rootView.findViewById(R.id.studentName);
        showStudentName=(TextView)rootView.findViewById(R.id.showStudentName);
        textView=(TextView)rootView.findViewById(R.id.aboutAppText);

        imageProfile=(ImageView)rootView.findViewById(R.id.imageProfile);
        imageProfile.setVisibility(View.INVISIBLE);

        prefVar = getActivity().getSharedPreferences(key, Context.MODE_PRIVATE);


        if(prefVar.contains(key))
        {
            button.setVisibility(View.INVISIBLE);
            studentName.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);

            imageProfile.setVisibility(View.VISIBLE);

            if(counter==0) {
                showStudentName.setVisibility(View.INVISIBLE);
                counter++;
            }
            showStudentName.setText(prefVar.getString(key, ""));


            imageProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showStudentName.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Welcome Back " + prefVar.getString(key, "") + "!", Toast.LENGTH_SHORT).show();


                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentName();
            }
        });
        return rootView;

    }

    private void saveStudentName() {
        String name = studentName.getText().toString();
        SharedPreferences.Editor editor = prefVar.edit();
        editor.putString(key, name);
        editor.commit();

        Toast.makeText(getActivity(), "Profile Saved", Toast.LENGTH_SHORT).show();

        button.setVisibility(View.INVISIBLE);
        studentName.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        imageProfile.setVisibility(View.VISIBLE);
        showStudentName.setText(prefVar.getString(key, ""));


    }

}
