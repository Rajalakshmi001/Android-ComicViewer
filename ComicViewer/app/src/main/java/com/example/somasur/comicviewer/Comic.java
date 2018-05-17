package com.example.somasur.comicviewer;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Comic implements Parcelable{

    private int num;
    private int month;
    private int day;
    private int year;
    private String link;
    private String news;
    private String transcript;
    private String safe_title;
    private String alt;
    private String img;
    private String title;
    private int Issue;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private Bitmap image;

    public Comic(){
        //default no-argument constructor
    }

    protected Comic(Parcel in) {
        num = in.readInt();
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
        link = in.readString();
        news = in.readString();
        transcript = in.readString();
        safe_title = in.readString();
        alt = in.readString();
        img = in.readString();
        title = in.readString();
    }

    public void setComic(Comic comic) {
        this.num = comic.num;
        this.month = comic.month;
        this.day = comic.day;
        this.year = comic.year;
        this.link = comic.link;
        this.news = comic.news;
        this.transcript = comic.transcript;
        this.safe_title = comic.safe_title;
        this.alt = comic.alt;
        this.img = comic.img;
        this.title = comic.title;
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    public String toString(int data){
        return data + "";
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public void setSafe_title(String safe_title) {
        this.safe_title = safe_title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssue(){
        return Utils.getRandomCleanIssue();
    }
    public void setIssue(int issue) {
        Issue = issue;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(num);
        parcel.writeInt(month);
        parcel.writeInt(day);
        parcel.writeInt(year);
        parcel.writeString(link);
        parcel.writeString(news);
        parcel.writeString(transcript);
        parcel.writeString(safe_title);
        parcel.writeString(alt);
        parcel.writeString(img);
        parcel.writeString(title);
    }
}
