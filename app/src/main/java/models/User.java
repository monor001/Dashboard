package models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by asus on 12.02.2017.
 */

public class User {

    @SerializedName("id")
    int userId;

    @SerializedName("userName")
    String userName = "";

    @SerializedName("email")
    String Email = "" ;

    @SerializedName("PW")
    String PW = "";

    @SerializedName("facebook")
    Boolean facebook = false;

    @SerializedName("gmail")
    Boolean gmail = false;

    @SerializedName("userCourseslist")
    HashMap<String, Course> userCourseslist = new HashMap<String, Course>();

    @SerializedName("userChapterlist")
    HashMap<String, Chapter> userChapterlist = new HashMap<String, Chapter>();

    @SerializedName("No_Notification_Day")
    int No_Notifications_Day = 0;

    Statistics statistics = new Statistics();
    Flipcards flipcards = new Flipcards();

    @SerializedName("finishedChapter")
    int finishedChapters = 0;

    @SerializedName("finishedCourses")
    int finishedCourses = 0;

    public HashMap<String, Chapter> getUserChapterlist() {
        return userChapterlist;
    }

    public void setUserChapterlist(HashMap<String, Chapter> userChapterlist) {
        this.userChapterlist = userChapterlist;
    }

    public HashMap<String, Course> getUserCourseslist() {
        return userCourseslist;
    }

    public void setUserCourseslist(HashMap<String, Course> userCourseslist) {
        this.userCourseslist = userCourseslist;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getFacebook() {
        return facebook;
    }

    public void setFacebook(Boolean facebook) {
        this.facebook = facebook;
    }

    public int getFinishedChapters() {
        return finishedChapters;
    }

    public void setFinishedChapters(int finishedChapters) {
        this.finishedChapters = finishedChapters;
    }

    public int getFinishedCourses() {
        return finishedCourses;
    }

    public void setFinishedCourses(int finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

    public Flipcards getFlipcards() {
        return flipcards;
    }

    public void setFlipcards(Flipcards flipcards) {
        this.flipcards = flipcards;
    }

    public Boolean getGmail() {
        return gmail;
    }

    public void setGmail(Boolean gmail) {
        this.gmail = gmail;
    }

    public int getNo_Notifications_Day() {
        return No_Notifications_Day;
    }

    public void setNo_Notifications_Day(int no_Notifications_Day) {
        No_Notifications_Day = no_Notifications_Day;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
