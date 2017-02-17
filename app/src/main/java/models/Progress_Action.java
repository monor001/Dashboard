package models;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by asus on 06.02.2017.
 */

public class Progress_Action {
    public ProgressBar progressBar_P_A;
    public TextView textView_P_A;
    public int progress;
    public Handler handler;
    public Runnable runnable;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
