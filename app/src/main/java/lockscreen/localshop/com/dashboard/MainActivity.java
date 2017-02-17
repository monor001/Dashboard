package lockscreen.localshop.com.dashboard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.Exclude;
import Controller.SessionManager;
import models.Course;
import models.Progress_Action;
import models.User;


public class MainActivity extends AppCompatActivity implements Serializable{
    // Attributes to handle circle progress
    ArrayList<Progress_Action> progressBar_arr = new ArrayList<Progress_Action>();
    ArrayList<Course> courses_alltimes = new ArrayList<Course>();;
    ArrayList<Button> courses_btns_alltimes = new ArrayList<Button>();
    ArrayList<Button> courses_btns_weekly = new ArrayList<Button>();
    RelativeLayout rL_progressCircles;
    RelativeLayout courses_lL0, courses_lL1;
    int intital_value=0;

    // Session Manager Class
    SessionManager session;
    Exclude ex;
    Gson gson;
    String json_user;
    User user;
    LinearLayout lLEnterCourse_MA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new SessionManager(getApplicationContext());
        ex = new Exclude();
        gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        json_user = session.pref.getString(SessionManager.JSON, "");
        user = gson.fromJson(json_user, User.class);

        // Call LinearLayout that holds courses
        lLEnterCourse_MA = (LinearLayout) findViewById(R.id.lLEnterCourse_MA);

        // Setting up circle progress
        GridLayout gridLayout_progress = (GridLayout) findViewById(R.id.gl_circleSta_MA);
        // extra_progress value could be change according to the user progress
        int extra_progress = 30;
        for(int i=0;i<gridLayout_progress.getChildCount();i++){
            rL_progressCircles =(RelativeLayout)gridLayout_progress.getChildAt(i);
            final Progress_Action progress_action = new Progress_Action();
            for (int j = 0; j< rL_progressCircles.getChildCount(); j++){
                if(rL_progressCircles.getChildAt(j) instanceof ProgressBar){
                    progress_action.progressBar_P_A = (ProgressBar) rL_progressCircles.getChildAt(j);

                }else if (rL_progressCircles.getChildAt(j) instanceof TextView){
                    progress_action.textView_P_A = (TextView) rL_progressCircles.getChildAt(j);
                }
                progress_action.handler = new Handler();
                progress_action.setProgress(extra_progress);
                progress_action.runnable = new Runnable(){
                    int intital_value=0;
                    public void run() {
                        if(intital_value<progress_action.getProgress()){
                            intital_value++;
                            progress_action.textView_P_A.setText(""+intital_value);
                        }
                        progress_action.handler.postDelayed(this, 10);

                    }
                };
                extra_progress=extra_progress+10;
            }

            if(progress_action.progressBar_P_A!=null&&progress_action.textView_P_A!=null){
                progressBar_arr.add(i, progress_action);
                ObjectAnimator animation1 = ObjectAnimator.ofInt (progressBar_arr.get(i).progressBar_P_A, "progress", intital_value, progressBar_arr.get(i).getProgress()); // see this max value coming back here, we animale towards that value
                animation1.setDuration (4000); //in milliseconds
                animation1.setInterpolator (new DecelerateInterpolator());
                animation1.start ();
                progressBar_arr.get(i).handler.post(progressBar_arr.get(i).runnable);
            }
        }

        /* Create courses view on the first creation*/
        onFirstcreate ();

        /*// Getting all courses at the view
        final ScrollView s1EnterCourse_MA = (ScrollView) findViewById(R.id.s1EnterCourse_MA);
        final ScrollView s2EnterCourse_MA = (ScrollView) findViewById(R.id.s2EnterCourse_MA);
        LinearLayout lLEnterCourse_MA = (LinearLayout) findViewById(R.id.lLEnterCourse_MA);
        LinearLayout lL222EnterCourse_MA = (LinearLayout) findViewById(R.id.lL222EnterCourse_MA);
        Button course_btn;
        ProgressBar course_progressbar;
        Course course = new Course();

        for (int courses_counter = 0; courses_counter < lLEnterCourse_MA.getChildCount(); courses_counter++){
            courses_lL0 = (RelativeLayout) lLEnterCourse_MA.getChildAt(0);
            courses_lL1 = (RelativeLayout) lL222EnterCourse_MA.getChildAt(0);
            if(courses_alltimes!=null){
                courses_alltimes.removeAll(courses_alltimes);
            }
            // Add new courses depending on the selection from the second intent

            *//*------------------------------------------------------*//*
            if(courses_lL0.getChildAt(0) instanceof Button){
                course_btn = (Button) courses_lL0.getChildAt(0);
                course_progressbar = (ProgressBar) courses_lL0.getChildAt(1);
                course.setBtn(course_btn);
                course.setProgressBar_P_A(course_progressbar);
                //courses_btns_weekly.add(courses_counter, (Button) courses_lL1.getChildAt(0));
            }
        }

        // Setting up courses view transition
        final Button B_Weekly_MA = (Button) findViewById(R.id.B_Weekly_MA);
        final Button B_AllTime_MA = (Button) findViewById(R.id.B_AllTime_MA);

        final AlphaAnimation alphaAnimation_1 = new AlphaAnimation(0,1);
        alphaAnimation_1.setDuration(200);
        final AlphaAnimation alphaAnimation_2 = new AlphaAnimation(1,0);
        alphaAnimation_2.setDuration(200);
        B_Weekly_MA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==B_Weekly_MA.getId()) {
                    // On Weekly button
                    ValueAnimator colorAnimation1 = ValueAnimator.ofObject(new ArgbEvaluator(), 0, 0xffffffff);
                    colorAnimation1.setDuration(300); // milliseconds
                    colorAnimation1.addUpdateListener(new AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            B_Weekly_MA.setBackgroundColor((int) animator.getAnimatedValue());
                            B_Weekly_MA.setTextColor(0xFF0A2247);
                            s1EnterCourse_MA.setAlpha(0);
                        }

                    });
                    // Off Alltime button
                    ValueAnimator colorAnimation2 = ValueAnimator.ofObject(new ArgbEvaluator(), 0xffffffff, 0);
                    colorAnimation1.setDuration(300); // milliseconds
                    colorAnimation1.addUpdateListener(new AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            B_AllTime_MA.setBackgroundColor((int) animator.getAnimatedValue());
                            B_AllTime_MA.setBackgroundResource(R.drawable.strock);
                            B_AllTime_MA.setTextColor(0xFFFFFFFF);
                            s2EnterCourse_MA.setAlpha(1);

                        }

                    });
                    colorAnimation1.start();
                    colorAnimation2.start();
                }

            }
        });
        B_AllTime_MA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==B_AllTime_MA.getId()){
                    // Off Weekly button
                    ValueAnimator colorAnimation1 = ValueAnimator.ofObject(new ArgbEvaluator(), 0xffffffff, 0);
                    colorAnimation1.setDuration(300); // milliseconds
                    colorAnimation1.addUpdateListener(new AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            B_Weekly_MA.setBackgroundColor((int) animator.getAnimatedValue());
                            B_Weekly_MA.setBackgroundResource(R.drawable.strock);
                            B_Weekly_MA.setTextColor(0xFFFFFFFF);
                            s1EnterCourse_MA.setAlpha(1);
                        }

                    });
                    // On Alltime button
                    ValueAnimator colorAnimation2 = ValueAnimator.ofObject(new ArgbEvaluator(), 0, 0xffffffff);
                    colorAnimation2.setDuration(300); // milliseconds
                    colorAnimation2.addUpdateListener(new AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            B_AllTime_MA.setBackgroundColor((int) animator.getAnimatedValue());
                            B_AllTime_MA.setTextColor(0xFF0A2247);
                            s2EnterCourse_MA.setAlpha(0);

                        }

                    });
                    colorAnimation1.start();
                    colorAnimation2.start();
                }
            }
        });*/

        /*// Setting animation for courses progress bar
        for (int coursecount=0;coursecount<lLEnterCourse_MA.getChildCount();coursecount++){

        }

        // This part regarding the process to pass courses to new topic view (From the douple layers)
        for (int coursecount=0;coursecount<lLEnterCourse_MA.getChildCount();coursecount++){
            courses_lL0 = (RelativeLayout) lLEnterCourse_MA.getChildAt(0);
            courses_lL1 = (RelativeLayout) lL222EnterCourse_MA.getChildAt(0);
            if(courses_lL0.getChildAt(0) instanceof Button){
                courses_btns_alltimes.add(coursecount, (Button) courses_lL0.getChildAt(0));
            }
        }*/
        // Setup extra buttons
        callTopicSelection();
    }

    public static Intent makeIntent(Context context) {
        Intent newIntent = new Intent(context, MainActivity.class);
        return newIntent;
    }


    /* Show all selected courses*/
    void onFirstcreate (){
        for (String courseName : user.getUserCourseslist().keySet()){
            if(user.getUserCourseslist().get(courseName).isCourseActive()){
                // Change display status
                onChangeDisplaystatus(courseName);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Session class instance
        session = new SessionManager(getApplicationContext());
        ex = new Exclude();
        gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        json_user = session.pref.getString(SessionManager.JSON, "");
        user = gson.fromJson(json_user, User.class);

        // Display all chosen courses
        for (String courseName : user.getUserCourseslist().keySet()){
            // Check if the course is active
            if(user.getUserCourseslist().get(courseName).isCourseActive()){
                // Check if the course is not displayed
                if(!user.getUserCourseslist().get(courseName).isDisplayed()){
                    // Change display status
                    onChangeDisplaystatus(courseName);
                }
            } if(user.getUserCourseslist().get(courseName).isDisplayed()){
                    if(!user.getUserCourseslist().get(courseName).isCourseActive()){
                        // Remove course from the view
                        for (int courseCounter=0; courseCounter<lLEnterCourse_MA.getChildCount();courseCounter++){
                            RelativeLayout relativeLayout = (RelativeLayout) lLEnterCourse_MA.getChildAt(courseCounter);
                            TextView textView = (TextView) relativeLayout.getChildAt(0);
                            if(user.getUserCourseslist().get(courseName).getCourseName().equals(textView.getText().toString())){
                                lLEnterCourse_MA.removeViewAt(courseCounter);
                                user.getUserCourseslist().get(courseName).setDisplayed(false);
                            }
                        }

                    }
                }
        }
        Gson gson1 = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        String json_user1 = gson1.toJson(user);
        session.editUserSession(json_user1);
    }


    void createCourseView (String courseName){
        //RelativeLayout rLChild = (RelativeLayout) lLEnterCourse_MA.getChildAt(courseCounter);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        // Create a new RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(this);
        // Defining the RelativeLayout layout parameters.
        // In this case I want to fill its parent
        RelativeLayout.LayoutParams rlprams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlprams.setMargins(0, 0, 0, (int)((10 * displayMetrics.density) + 0.5));
        relativeLayout.setLayoutParams(rlprams);

        // Create a new Button
        Button button = new Button(this);
        button.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        button.setText(user.getUserCourseslist().get(courseName).getCourseName());
        button.setBackgroundResource(R.drawable.strock);
        button.setTextColor(Color.parseColor("#ffffff"));
        button.setMinHeight((int)((5 * displayMetrics.density) + 0.5));
        button.setMinWidth(0);


        // Create a new progressbar
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, (int)((10 * displayMetrics.density) + 0.5), 0, 0);
        progressBar.setLayoutParams(layoutParams);
        progressBar.setProgress(50);
        // Add elements to Relativeview
        relativeLayout.addView(button, 0);
        relativeLayout.addView(progressBar, 1);
        // Add the created RelativeLayout to the view
        lLEnterCourse_MA.addView(relativeLayout);
    }

    void onChangeDisplaystatus(String courseName){
        user.getUserCourseslist().get(courseName).setDisplayed(true);
        Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        String json_user = gson.toJson(user);
        session.editUserSession(json_user);
        createCourseView (courseName);
    }

    // Call More Courses activity
    void callTopicSelection(){
        Button b1Extra_MA = (Button) findViewById(R.id.newTopicBExtra_MA);
        b1Extra_MA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewTopic = MoreCourses.makeIntent(MainActivity.this);
                startActivity(intentNewTopic);
            }
        });
    }



}
