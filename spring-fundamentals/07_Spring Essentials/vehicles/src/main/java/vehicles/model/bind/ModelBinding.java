package vehicles.model.bind;

import vehicles.constant.Constants;
import vehicles.model.enums.VehicleCategory;

public class ModelBinding {
    private String name;
    private VehicleCategory category = Constants.DEFAULT_CATEGORY;
    private Integer startYear;
    private Integer endYear;
    private String brand;
    private String imageUrl;

    public ModelBinding() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
