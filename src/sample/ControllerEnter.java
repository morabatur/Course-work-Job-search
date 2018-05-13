package sample;

import com.sun.org.apache.xerces.internal.util.TeeXMLDocumentFilterImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerEnter {

    public static String titel = "";
    @FXML
    private TextField field_ID;
    @FXML
    private TextField field_NameSurname;


    @FXML
    private void initialize() throws SQLException {
        try {
            DataBaseConnector.startConnection();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка!");
            alert.setHeaderText("Не вдалось встановити з'єднання!");
            alert.setContentText("Будь ласка повторіть спробу.");

            alert.showAndWait();
        }
    }

    /*
    Кнопка входу
     */
    public void btn_Enter(ActionEvent actionEvent) throws SQLException {
        System.err.println(field_NameSurname.getText());
        System.err.println(field_ID.getText());
        if (DataBaseConnector.autorization(field_ID.getText(), field_NameSurname.getText().trim())) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Stage stg = new Stage();
            stg.setScene(new Scene(root, 800, 600));
            stg.setTitle("Бюро пошуку роботи: " + field_NameSurname.getText());
            stg.show();
            Main.homeStage.close();
            DataBaseConnector.startConnection();
            //DataBaseConnector.getCon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка!");
            alert.setHeaderText("Невірно введені дані!");
            alert.setContentText("Будь ласка перевірте правильність вводу та повторіть спробу.");

            alert.showAndWait();
        }
    }

}
