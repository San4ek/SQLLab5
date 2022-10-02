import java.sql.Date;

public class Product {

    private int id;
    private String name;
    private int price;
    private Date delivery_date;
    private int id_manufacturer;
    private int quantity;
    private int manufacturer_price;
    private String uunit_of_measurement;
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

    public int getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(int id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getManufacturer_price() {
        return manufacturer_price;
    }

    public void setManufacturer_price(int manufacturer_price) {
        this.manufacturer_price = manufacturer_price;
    }

    public String getUunit_of_measurement() {
        return uunit_of_measurement;
    }

    public void setUunit_of_measurement(String uunit_of_measurement) {
        this.uunit_of_measurement = uunit_of_measurement;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}
