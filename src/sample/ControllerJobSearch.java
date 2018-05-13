package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ControllerJobSearch {
    @FXML
    private TableView jobTable;
    @FXML
    private TableColumn<JobPojo, String> company;
    @FXML
    private TableColumn<JobPojo, String> position;
    @FXML
    private TableColumn<JobPojo, String> salary;
    @FXML
    private TableColumn<JobPojo, String> qualification;
    @FXML
    private ComboBox<String> comboBoxCandidates;

    @FXML
    private void initialize() throws SQLException {
        setComboBoxItems();
        company.setCellValueFactory(new PropertyValueFactory<JobPojo, String>("company"));
        position.setCellValueFactory(new PropertyValueFactory<JobPojo, String>("position"));
        salary.setCellValueFactory(new PropertyValueFactory<JobPojo, String>("salary"));
        qualification.setCellValueFactory(new PropertyValueFactory<JobPojo, String>("qualification"));

    }

  //  private ObservableList<String> comboBoxCandidatesData = FXCollections.observableArrayList();

    private void setComboBoxItems() throws SQLException {

        comboBoxCandidates.getItems().setAll(DataBaseConnector.getCandidates());


    }

    private ObservableList<JobPojo> contractData = FXCollections.observableArrayList();

    public void buttonSearchJob(ActionEvent actionEvent) {
        String str = comboBoxCandidates.getValue();
        String mass[] = str.split(" ");


        for (JobPojo cont : DataBaseConnector.getSearchJob(mass[0])){
            contractData.add(cont);

        }
        jobTable.setItems(contractData);
    }
}
