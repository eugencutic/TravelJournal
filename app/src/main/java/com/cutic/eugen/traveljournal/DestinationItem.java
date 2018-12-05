package com.cutic.eugen.traveljournal;

public class DestinationItem {
    private String mTitle;
    private String mLocation;

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

    @Override
    public String toString() {
        return "DestinationItem{" +
                "Title='" + mTitle + '\'' +
                ", Destination='" + mLocation + '\'' +
                '}';
    }
}
