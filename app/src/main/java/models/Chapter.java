package models;

/**
 * Created by asus on 11.02.2017.
 */

public class Chapter {
    int chapterId;
    String chapterName;
    int chapter_progress;
    boolean chapterActivate;

    public int getChapter_progress() {
        return chapter_progress;
    }

    public void setChapter_progress(int chapter_progress) {
        this.chapter_progress = chapter_progress;
    }

    public boolean isChapterActivate() {
        return chapterActivate;
    }

    public void setChapterActivate(boolean chapterActivate) {
        this.chapterActivate = chapterActivate;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
