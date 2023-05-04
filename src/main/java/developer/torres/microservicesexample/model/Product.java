package developer.torres.microservicesexample.model;

import org.springframework.web.bind.annotation.ModelAttribute;
public class Product {

    //#region Attributes
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String Observation;
    //#endregion

    //#region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }
    //#endregion
}