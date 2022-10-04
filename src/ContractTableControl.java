import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ContractTableControl implements Initializable {
    @FXML
    private Button back;

    @FXML
    private TableView<Contract> contractTable;

    @FXML
    private TableColumn<Contract, Integer> contract_id;

    @FXML
    private TableColumn<Contract, Date> date;

    @FXML
    private TableColumn<Contract, String> activity;

    @FXML
    private TableColumn<Contract, Integer> manufacturer_id;

    @FXML
    private TableColumn<Contract, Integer> product_id;

    @FXML
    private TableColumn<Contract, Integer> provider_id;

    DatabaseHandler databaseHandler=new DatabaseHandler();
    FXMLConst fxmlConst=new FXMLConst();

    private final ObservableList<Contract> data = FXCollections.observableArrayList();
    private final ObservableList<String> choiceBox=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contractTable.setEditable(true);

        choiceBox.addAll("active", "not active");

        back.setOnAction(event -> fxmlConst.showScene(FXMLConst.MAIN_MENU_FXML, back));

        addInformationAboutContract();

        contract_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        product_id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        product_id.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(String.valueOf(item));
                    setTooltip(getTooltip.getProductTooltip(databaseHandler.getProductById(item)));
                    setOnMouseClicked(event -> {
                        if (event.getButton().name().equals(MouseButton.SECONDARY.name()) && event.getClickCount()==2) fxmlConst.showScene(FXMLConst.PRODUCT_TABLE_FXML, back);
                    });
                }
            }
        });

        manufacturer_id.setCellValueFactory(new PropertyValueFactory<>("id_manufacturer"));
        manufacturer_id.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean b) {
                super.updateItem(item, b);
                if (item!=null) {
                    setText(String.valueOf(item));
                    setTooltip(getTooltip.getProvOrManTooltip(databaseHandler.getManufacturerById(item), dbConst.MANUFACTURER));
                }
            }
        });

        provider_id.setCellValueFactory(new PropertyValueFactory<>("id_provider"));
        provider_id.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean b) {
                super.updateItem(item, b);
                if (item!=null) {
                    setText(String.valueOf(item));
                    setTooltip(getTooltip.getProvOrManTooltip(databaseHandler.getProviderById(item), dbConst.PROVIDER));
                }
            }
        });

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        activity.setCellFactory(ChoiceBoxTableCell.forTableColumn(choiceBox));
        activity.setOnEditCommit(item -> {
            if (!item.getOldValue().equals(item.getNewValue())) {
                item.getTableView().getItems().get(item.getTablePosition().getRow()).setActivity(item.getNewValue().trim());
                databaseHandler.updateActiveContract(item.getNewValue(), item.getRowValue().getId());
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
                contract.setActivity(contracts.getString(6));

                data.add(contract);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
