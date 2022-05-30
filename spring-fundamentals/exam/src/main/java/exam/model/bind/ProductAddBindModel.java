package exam.model.bind;

import exam.constant.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static exam.constant.Constants.DATE_TIME_FORMAT;

public class ProductAddBindModel {

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private String category;

    public ProductAddBindModel() {
    }

    @NotEmpty
    @Size(min = 3, max = 20, message = Constants.PRODUCT_NAME_LENGTH_MESSAGE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 5, message = Constants.PRODUCT_DESCRIPTION_LENGTH_MESSAGE)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0", message = Constants.PRODUCT_PRICE_MESSAGE)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @FutureOrPresent(message = Constants.DATETIME_CANNOT_BE_IN_PAST)
    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    @NotEmpty(message = Constants.PRODUCT_CATEGORY_MESSAGE)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
