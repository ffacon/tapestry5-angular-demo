package dev.openshift.tapestry.angular.data;


public class Camera {

    private String[] features;
    private String primary;

    public Camera() {
    }

    public String[] getFeatures() {
        return features;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
