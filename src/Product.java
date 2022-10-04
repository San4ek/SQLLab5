import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private int price;
    private Date delivery_date;
    private int manufacturer_id;
    private String quantity;
    private int manufacturer_price;
    private String unit_of_measurement;
    private int provider_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getManufacturer_price() {
        return manufacturer_price;
    }

    public void setManufacturer_price(int manufacturer_price) {
        this.manufacturer_price = manufacturer_price;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public void setUnit_of_measurement(String unit_of_measurement) {
        this.unit_of_measurement = unit_of_measurement;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}
