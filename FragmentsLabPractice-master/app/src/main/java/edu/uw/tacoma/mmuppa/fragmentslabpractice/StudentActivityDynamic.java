package edu.uw.tacoma.mmuppa.fragmentslabpractice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StudentActivityDynamic extends Activity
                implements StudentListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        if (savedInstanceState != null) return;

        if (findViewById(R.id.student_fragment_container) != null) {
            // Create an instance of ExampleFragment
            StudentListFragment firstFragment = new StudentListFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction()
                    .add(R.id.student_fragment_container, firstFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int id) {
      // Capture the student fragment from the activity layout
        StudentFragment studentFragment = (StudentFragment)
                getFragmentManager().findFragmentById(R.id.student_fragment);

        if (studentFragment != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the student fragment to update its content
            studentFragment.updateStudentView(id);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected student
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            StudentFragment newFragment = new StudentFragment();
            Bundle args = new Bundle();
            args.putInt(StudentFragment.ARG_POSITION, id);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.student_fragment_container, newFragment)
                    .addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
