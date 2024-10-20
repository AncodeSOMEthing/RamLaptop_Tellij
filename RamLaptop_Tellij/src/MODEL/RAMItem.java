package MODEL;

import java.io.Serializable;

//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            Phật phù hộ, không bao giờ BUG
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public class RAMItem implements Serializable {
    private static final long serialVersionUID = 5675341960830470052L;


    private static int counter = 0; // Biến đếm để tạo mã tự động
    private String code;
    private String type;
    private int bus;
    private String brand;
    private int quantity;
    private String productionMonthYear;
    private boolean active;

    // Constructor
    public RAMItem(String code ,String type, int bus, String brand, int quantity, String productionMonthYear) {
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.active = true;
        this.code = code; // Tạo mã code tự động
    }



    // Getters và Setters
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

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduction_Month_Year() {
        return productionMonthYear;
    }

    public void setProduction_Month_Year(String production_Month_Year) {
        this.productionMonthYear = production_Month_Year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public String toString() {
        return "Item ID: " + code + " | Type: " + type + " | Bus: " + bus + " | Brand: " + brand +
                " | Quantity: " + quantity + " | Production Date: " + productionMonthYear +
                " |" ;
    }
}
