package com.cutic.eugen.traveljournal;

import java.util.Date;



public class Trip {
    private String ID;
    private String mTitle;
    private String mDestination;

    private TripType mTripType;

    private int mPrice;
    private double mRating;
    private Date mStartDate;
    private Date mEndDate;
    private Boolean mIsFavourite = false;

    public Trip() {

    }

    public Trip(String mTitle, String mDestination) {
        this.mTitle = mTitle;
        this.mDestination = mDestination;
    }

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

    public Boolean getIsFavourite() {
        return mIsFavourite;
    }

    public void setIsFavourite(Boolean mIsFavourite) {
        this.mIsFavourite = mIsFavourite;
    }

    public TripType getTripType() {
        return mTripType;
    }

    public void setTripType(TripType mTripType) {
        this.mTripType = mTripType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
