import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DatabaseHandler {
    Connection dbConnection;
    ResultSet resSet = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + Configs.dbHost + ":" + Configs.dbPort + "/" + Configs.dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPass);
        return dbConnection;
    }

    public ResultSet getContracts() {
        String select = "SELECT * FROM " + dbConst.CONTRACT_TABLE_DB;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProducts() {
        String select = "SELECT * FROM " + dbConst.PRODUCT_TABLE_DB;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProductById(int id_product) {
        String select = "SELECT * FROM " + dbConst.PRODUCT_TABLE_DB+" WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id_product));

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProviderById(int id_product) {
        String select = "SELECT * FROM " + dbConst.PROV_TABLE_DB +" WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id_product));

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getManufacturerById(int id_product) {
        String select = "SELECT * FROM " + dbConst.MAN_TABLE_DB + " WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id_product));

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void updateActiveContract(String active, int id_contract) {
        String update = "UPDATE " + dbConst.CONTRACT_TABLE_DB + " SET activity=? WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,active);
            prSt.setString(2, String.valueOf(id_contract));

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateProductQuantity(int id_product, int quantity) {
        String update = "UPDATE " + dbConst.PRODUCT_TABLE_DB + " SET quantity=? WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,String.valueOf(quantity));
            prSt.setString(2, String.valueOf(id_product));

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
