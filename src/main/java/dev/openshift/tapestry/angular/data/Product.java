package dev.openshift.tapestry.angular.data;


public class Product {

    private String title;
    private Double price;
    private Long id;
    private String assetLocation;

    public Product(long i,String title, Double j,String assetURI) {
        super();
        this.id = i;
        this.title = title;
        this.price = j;
        this.assetLocation = assetURI;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
    }
    public String getAssetLocation() {
        return assetLocation;
    }
}