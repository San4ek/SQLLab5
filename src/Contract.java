import java.sql.Date;

public class Contract {
    private int id;
    private int id_product;
    private int id_manufacturer;
    private Date date;
    private int id_provider;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(int id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public int getId_provider() {
        return id_provider;
    }

    public void setId_provider(int id_provider) {
        this.id_provider = id_provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
