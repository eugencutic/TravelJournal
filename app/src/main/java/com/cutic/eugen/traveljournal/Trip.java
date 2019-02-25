package com.cutic.eugen.traveljournal;

import java.util.Date;

public class Trip {
    private int ID;
    private String mTitle;
    private String mDestination;

    private enum mTripType {
        CITY_BREAK,
        SEASIDE,
        MOUNTAINS;
    }

    private int mPrice;
    private double mRating;
    private Date mStartDate;
    private Date mEndDate;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String mDestination) {
        this.mDestination = mDestination;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(double mRating) {
        this.mRating = mRating;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date mEndDate) {
        this.mEndDate = mEndDate;
    }
}
