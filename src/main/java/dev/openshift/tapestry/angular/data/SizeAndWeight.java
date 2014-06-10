package dev.openshift.tapestry.angular.data;


public class SizeAndWeight {

    private String[] dimensions;
    private String weight;

    public SizeAndWeight() {
    }

    public String[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
