import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductTableControl implements Initializable {

    @FXML
    private TableView<Contract> contractTable;

    @FXML
    private TableColumn<Contract, Integer> contract_id;

    @FXML
    private TableColumn<Contract, Date> date;

    @FXML
    private TableColumn<Contract, Contract> delete;

    @FXML
    private TableColumn<Contract, Integer> manufacturer_id;

    @FXML
    private TableColumn<Contract, Integer> product_id;

    @FXML
    private TableColumn<Contract, Integer> provider_id;

    DatabaseHandler databaseHandler=new DatabaseHandler();

    private final ObservableList<Contract> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addInformationAboutContract();

        contract_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_id.setCellValueFactory(new PropertyValueFactory<>("id_product"));

        product_id.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(String.valueOf(item));
               if (item != null) {
                   setTooltip(getProductTooltip(databaseHandler.getProductById(item)));
               }
            }
        });

        manufacturer_id.setCellValueFactory(new PropertyValueFactory<>("id_manufacturer"));
        provider_id.setCellValueFactory(new PropertyValueFactory<>("id_provider"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        delete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Contract contract, boolean empty) {
                super.updateItem(contract, empty);

                if (contract == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
            }
        });

        contractTable.setItems(data);

    }

    private void addInformationAboutContract() {
        ResultSet contracts=databaseHandler.getContracts();
        try {
            while (contracts.next()) {
                Contract contract=new Contract();

                contract.setId(Integer.parseInt(contracts.getString(1)));
                contract.setId_product(Integer.parseInt(contracts.getString(2)));
                contract.setId_manufacturer(Integer.parseInt(contracts.getString(3)));
                contract.setDate(Date.valueOf(contracts.getString(4)));
                contract.setId_provider(Integer.parseInt(contracts.getString(5)));

                data.add(contract);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Tooltip getProductTooltip(ResultSet products) {
        try {
            products.next();
            Tooltip toolTip=new Tooltip(("ID : " + products.getString(1) + "\n" +
                    "Name : " + products.getString(2) + "\n" +
                    "Price : " + products.getString(3) + "\n" +
                    "Date of delivery : " + products.getString(4) + "\n" +
                    "Manufacturer's id : " + products.getString(5) + "\n" +
                    "Quantity : " + products.getString(6) + products.getString(8) + "\n" +
                    "Manufacturer's price : " + products.getString(7) + "\n" +
                    "Provider's id : " + products.getString(9)));
            toolTip.setStyle("-fx-font-size : 14");
            return toolTip;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
