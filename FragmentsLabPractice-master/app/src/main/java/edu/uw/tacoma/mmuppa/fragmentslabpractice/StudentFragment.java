package edu.uw.tacoma.mmuppa.fragmentslabpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.tacoma.mmuppa.fragmentslabpractice.student.StudentList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment {

    public static final String ARG_POSITION = "POSITION" ;
    private int mCurrentPosition = -1;
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateStudentView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateStudentView(mCurrentPosition);
        }
    }
    public void updateStudentView(int pos) {
        TextView tv = (TextView) getActivity().findViewById(R.id.student_name);
        tv.setText((CharSequence) StudentList.ITEMS.get(pos).getName());
    }
    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }
}
