package com.example.bigwork;

import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable {
    private String type;
    private String result;
    private String pool;
    private String time;
    private String level;

    public Record(String type, String result, String pool, String time, String level) {
        this.type = type;
        this.result = result;
        this.pool = pool;
        this.time = time;
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public String getResult() {
        return result;
    }

    public String getPool() {
        return pool;
    }

    public String getTime() {
        return time;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(result);
        dest.writeString(pool);
        dest.writeString(time);
        dest.writeString(level);
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

    protected Record(Parcel in) {

        type = in.readString();
        result = in.readString();
        pool = in.readString();
        time = in.readString();
        level = in.readString();
    }
}