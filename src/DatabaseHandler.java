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
        String select = "SELECT * FROM " + Const.CONTRACT_TABLE_DB;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProductById(int id_product) {
        String select = "SELECT * FROM " + Const.PRODUCT_TABLE_DB+" WHERE id=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id_product));

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

}
