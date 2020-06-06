package vehicles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import vehicles.model.enums.EngineType;
import vehicles.model.enums.TransmissionType;
import vehicles.model.enums.VehicleCategory;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private VehicleCategory category;

    @NotNull
    @ManyToOne
    private Model model;

    @NotNull
    @PastOrPresent
    @Min(1900)
    private Integer year;

    @NotNull
    @Positive
    private Integer mileage;

    @NotNull
    private EngineType engine;

    @NotNull
    private TransmissionType transmission;

    @NotNull
    @Size(min = 2, max = 512)
    private String description;

    @NotNull
    @PastOrPresent
    @Positive
    private Double price;

    @NotNull
    @Size(min = 8, max = 512)
    private String imageUrl;

    @ManyToOne(optional = true)
    private User seller;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private Long sellerId;

    private Date created = new Date();
    private Date modified = new Date();

    public Offer() { }
    public Offer(@NotNull VehicleCategory category, @NotNull Model model, @NotNull @PastOrPresent @Min(1900) Integer year, @NotNull @Positive Integer mileage, @NotNull EngineType engine, @NotNull TransmissionType transmission, @NotNull @Size(min = 2, max = 512) String description, @NotNull @PastOrPresent @Positive Double price, @NotNull @Size(min = 8, max = 512) String imageUrl, User seller, Long sellerId) {
        this.category = category;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.seller = seller;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
