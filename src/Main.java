import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(Const.PRODUCT_TABLE_FXML));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root=loader.getRoot();
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}