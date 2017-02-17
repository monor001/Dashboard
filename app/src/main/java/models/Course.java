package models;

import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 11.02.2017.
 */

public class Course {

    //General attributes
    @SerializedName("courseId")
    int courseId;
    @SerializedName("courseDisplayId")
    int courseDisplayId;

    @SerializedName("courseName")
    String courseName = "";
    @SerializedName("courseProgress")
    int courseProgress = 0;

    // Indicators
    @SerializedName("courseActive")
    boolean courseActive = false;
    @SerializedName("displayed")
    boolean displayed = false;

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }



    Button btn;
    public ProgressBar progressBar_P_A;
    public Handler handler;
    public Runnable runnable;

    public Course() {
    }

    public Course(int courseId, String courseName, boolean courseActive, int courseProgress) {
        this.courseActive = courseActive;
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseProgress = courseProgress;
    }

    public boolean isCourseActive() {
        return courseActive;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ProgressBar getProgressBar_P_A() {
        return progressBar_P_A;
    }

    public void setProgressBar_P_A(ProgressBar progressBar_P_A) {
        this.progressBar_P_A = progressBar_P_A;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setCourseActive(boolean courseActive) {
        this.courseActive = courseActive;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(int courseProgress) {
        this.courseProgress = courseProgress;
    }
}
