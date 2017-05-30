package com.mangaapp.nexusninja2.mangayeah;

/**
 * Created by NexusNinja2 on 5/29/2017.
 */

public class Profile {

    private String mUsername;
    private String mFirstName;
    private String mLastName;
    private String mGender;

    public Profile(String mUsername, String mFirstName, String mLastName, String mGender, String mUserId) {
        this.mUsername = mUsername;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mGender = mGender;
        this.mUserId = mUserId;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    private String mUserId;


    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public Profile()
    {

    }
}
