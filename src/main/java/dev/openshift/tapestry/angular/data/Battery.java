package dev.openshift.tapestry.angular.data;


public class Battery {

    private String standbyTime;
    private String talkTime;
    private String type;

    public Battery() {
    }

    public String getStandbyTime() {
        return standbyTime;
    }

    public void setStandbyTime(String standbyTime) {
        this.standbyTime = standbyTime;
    }

    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
