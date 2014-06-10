package dev.openshift.tapestry.angular.data;


public class PhoneDetails {
    private String additionalFeatures;
    private Android android;
    private String[]  availability;
    private Battery battery;
    private Camera camera;
    private Connectivity connectivity;
    private String description;
    private Display display;
    private Hardware hardware;
    private String id;
    private String[] images;
    private String name;
    private SizeAndWeight sizeAndWeight;
    private Storage storage;

    public PhoneDetails() {
    }

    public String getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(String additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }

    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }

    public String[] getAvailability() {
        return availability;
    }

    public void setAvailability(String[] availability) {
        this.availability = availability;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Connectivity getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(Connectivity connectivity) {
        this.connectivity = connectivity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SizeAndWeight getSizeAndWeight() {
        return sizeAndWeight;
    }

    public void setSizeAndWeight(SizeAndWeight sizeAndWeight) {
        this.sizeAndWeight = sizeAndWeight;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
