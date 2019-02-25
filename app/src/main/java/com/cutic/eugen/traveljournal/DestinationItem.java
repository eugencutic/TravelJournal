package com.cutic.eugen.traveljournal;

public class DestinationItem {
    private String mId;
    private String mTitle;
    private String mLocation;
    private Trip mTrip;

    public DestinationItem(String mTitle, String mLocation) {
        this.mTitle = mTitle;
        this.mLocation = mLocation;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public Trip getTrip() {
        return mTrip;
    }

    public void setTrip(Trip mTrip) {
        this.mTrip = mTrip;
    }

    @Override
    public String toString() {
        return "DestinationItem{" +
                "Title='" + mTitle + '\'' +
                ", Destination='" + mLocation + '\'' +
                '}';
    }
}
