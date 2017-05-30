package com.mangaapp.nexusninja2.mangayeah;

/**
 * Created by NexusNinja2 on 5/25/2017.
 */

public class UserClass
{
    private String mUsername, mFirstName, mLastName, mGender, mUserID;

    public UserClass()
    {

    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

    public UserClass(String mUsername, String mFirstName, String mLastName, String mGender, String mUserID) {
        this.mUsername = mUsername;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mGender = mGender;
        this.mUserID = mUserID;

    }

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
}
