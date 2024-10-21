package Model;


import java.io.Serializable;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;


public class RAMmodel implements Serializable{
    private String code, type, brand;
    private int bus, quantity;
    private YearMonth productionDate;
    private boolean active;

    public RAMmodel() {
    }

    public RAMmodel(String code, String type, String brand, int bus, int quantity, YearMonth productionDate) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.bus = bus;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.active = true;
    }
    
   
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public YearMonth getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(YearMonth productionDate) {
        this.productionDate = productionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
public String toString() {
    
    String formattedDate = productionDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    return String.format(
            
        "Item ID: %-11s | Type: %-7s | Bus speed: %d MHz | Brand: %-10s | Quantity: %-4d | Release date: %-7s |",
        code, type, bus, brand, quantity, formattedDate
    );
}

    
    
}