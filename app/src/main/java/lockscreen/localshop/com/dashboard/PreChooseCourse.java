package lockscreen.localshop.com.dashboard;

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
import models.Course;
import models.User;

public class PreChooseCourse extends AppCompatActivity {
    // Initial required attributes
    User user_session = new User();

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prechoosecourse);
        setViewCourses ();
    }

    void setViewCourses (){
        // Initial data
        final User user = new User();
        user.setUserId(0);

        // Initial a carrier
        session = new SessionManager(getApplicationContext());

        // Initiate view components
        final LinearLayout sLL1_preCS = (LinearLayout)  findViewById(R.id.sLL1_preCS);
        final LinearLayout sLL0_preCS = (LinearLayout)  findViewById(R.id.sLL0_preCS);
        Button finish_preCS = (Button) findViewById(R.id.finish_preCS);

        // Get all assigned courses by the user
        finish_preCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int courseCounter_L = 0;
                for (int courseCounter_V = 0; courseCounter_V < sLL1_preCS.getChildCount()
                        ; courseCounter_V++ ){
                    ToggleButton toggleButton = (ToggleButton) sLL1_preCS.getChildAt(courseCounter_V);
                    TextView textView = (TextView) sLL0_preCS.getChildAt(courseCounter_V);
                    // Check toggle button activation
                    if(toggleButton.isChecked()){
                        Course course = new Course(courseCounter_V, textView.getText().toString(), true, 0);
                        user.getUserCourseslist().put(course.getCourseName(), course);
                        // Increase courseCounter_L indicator
                        courseCounter_L ++;
                    } else{
                        Course course = new Course(courseCounter_V, textView.getText().toString(), false, 0);
                        user.getUserCourseslist().put(course.getCourseName(), course);
                    }
                }
                if (!user.getUserCourseslist().isEmpty() && user.getUserCourseslist()!=null) {
                    // Go to the next view
                    user.setUserId(0);
                    Exclude ex = new Exclude();
                    Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
                    String json_user = gson.toJson(user);
                    session.creatLoginSession(user.getUserId(), false, user.getUserName(), user.getEmail(), json_user);
                    Intent intentSignIn = SignIn.makeIntent(PreChooseCourse.this);
                    startActivity(intentSignIn);
                    finish();
                }else{
                    // Send error message
                }
            }
        });
    }
}
