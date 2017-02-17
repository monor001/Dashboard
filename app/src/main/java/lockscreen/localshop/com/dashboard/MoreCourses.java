package lockscreen.localshop.com.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.Exclude;
import Controller.SessionManager;
import models.User;

public class MoreCourses extends AppCompatActivity {

    // Session Manager Class
    SessionManager session;
    Gson gson;
    String json_user;
    Exclude ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_courses);

        // Session class instance
        session = new SessionManager(getApplicationContext());
        ex = new Exclude();
        gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        json_user = session.pref.getString(SessionManager.JSON, "");
        final User user = gson.fromJson(json_user, User.class);

        // Call view components
        final LinearLayout sLL0_postCS = (LinearLayout) findViewById(R.id.sLL0_postCS);
        final LinearLayout sLL1_postCS = (LinearLayout) findViewById(R.id.sLL1_postCS);


        // Call finish Button
        Button finish_postCS = (Button) findViewById(R.id.finish_postCS);

        /* Change all checked toggle buttons in the view*/
        onCreateViewCourse(user, sLL0_postCS, sLL1_postCS);


        finish_postCS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onFinishView (user, sLL0_postCS, sLL1_postCS);

                // Destroy Activity
                finish();

                Gson gson1 = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
                String json_user1 = gson1.toJson(user);
                session.editUserSession(json_user1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Call view components
        final LinearLayout sLL0_postCS = (LinearLayout) findViewById(R.id.sLL0_postCS);
        final LinearLayout sLL1_postCS = (LinearLayout) findViewById(R.id.sLL1_postCS);

        final User user = gson.fromJson(json_user, User.class);
        onFinishView (user, sLL0_postCS, sLL1_postCS);
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MoreCourses.class);
        // intent.putExtra(PROGRESS_BAR_ARR, (Serializable) courses_btns);
        return intent;
    }

    void onCreateViewCourse (User user, LinearLayout sLL0_postCS, LinearLayout sLL1_postCS) {
        /* Change all checked toggle buttons in the view*/
        // Check all chosen courses from the view
        for (int courseCounter_V = 0; courseCounter_V < sLL1_postCS.getChildCount(); courseCounter_V++){

            // Call toggle buttons for check
            ToggleButton toggleButton = (ToggleButton) sLL1_postCS.getChildAt(courseCounter_V);

            // Change all checked toggle buttons in the view
            if(!toggleButton.isChecked()){
                // Change Toggle button status to on if its chosen in user list
                TextView textView = (TextView) sLL0_postCS.getChildAt(courseCounter_V);
                if (user.getUserCourseslist().containsKey(textView.getText().toString())){
                    if(user.getUserCourseslist().get(textView.getText().toString()).isCourseActive()){
                        toggleButton.setChecked(true);
                    }
                }
            }
        }
    }


    void onFinishView (User user, LinearLayout sLL0_postCS, LinearLayout sLL1_postCS){

        // Check all chosen courses from the view
        for (int courseCounter_V = 0; courseCounter_V < sLL1_postCS.getChildCount(); courseCounter_V++){

            // Call toggle buttons for check
            ToggleButton toggleButton = (ToggleButton) sLL1_postCS.getChildAt(courseCounter_V);
            TextView textView = (TextView) sLL0_postCS.getChildAt(courseCounter_V);

            // Change all checked toggle buttons in the view
            if(toggleButton.isChecked()){
                // Change Toggle button status to on if its chosen in user list
                user.getUserCourseslist().get(textView.getText().toString()).setCourseActive(true);
            } else{
                user.getUserCourseslist().get(textView.getText().toString()).setCourseActive(false);
            }
        }
    }
}
