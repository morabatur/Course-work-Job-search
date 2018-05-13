package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControllerMain {

    private ObservableList<VacancyPojo> vacancyData = FXCollections.observableArrayList();

    @FXML
    private TableView tableVacancy;
    @FXML
    private ComboBox<String> comboBoxCompany;
    @FXML
    private TableColumn<VacancyPojo, String> name;
    @FXML
    private TableColumn<VacancyPojo, String> kindOfActivity;
    @FXML
    private TableColumn<VacancyPojo, String> possition;
    @FXML
    private TableColumn<VacancyPojo, String> qualification;
    @FXML
    private TableColumn<VacancyPojo, String> profession;
    @FXML
    private TableColumn<VacancyPojo, String> salary;
    @FXML
    private TableColumn<VacancyPojo, String> dateOfPublic;
    /*
    ===========================================================
     */
    @FXML
    private TableView table_Summary;
    @FXML
    private TableColumn<SummaryPojo, String> fullName;
    @FXML
    private TableColumn<SummaryPojo, String> qualificationSummary;
    @FXML
    private TableColumn<SummaryPojo, String> professionSummary;
    @FXML
    private TableColumn<SummaryPojo, String> expectedSalary;
    @FXML
    private TableColumn<SummaryPojo, String> datePublicSummary;
    @FXML
    private ComboBox<String> comboBox_KindUnity;

    /*
    ==============================================================
     */

    @FXML
    private TableView table_contracts;
    @FXML
    private TableColumn<ContractPojo, String> id_contracts;
    @FXML
    private TableColumn<ContractPojo, String> date_contracts;
    @FXML
    private TableColumn<ContractPojo, String> name_Employers;
    @FXML
    private TableColumn<ContractPojo, String> name_candidates;
    @FXML
    private TableColumn<ContractPojo, String> position_name;
    @FXML
    private TableColumn<ContractPojo, String> feddback;
    @FXML
    private TableColumn<ContractPojo, String> agent;

    @FXML
    private void initialize() throws SQLException {


        setComboBoxItems();
        setComboBox_KindUnity();
        //initData();
        // устанавливаем тип и значение которое должно хранится в колонке

        name.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("name"));
        kindOfActivity.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("kindOfActivity"));
        possition.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("possition"));
        qualification.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("qualification"));
        profession.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("profession"));
        salary.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("salary"));
        dateOfPublic.setCellValueFactory(new PropertyValueFactory<VacancyPojo, String>("dateOfPublic"));
        /*
        ==================================================
         */
        fullName.setCellValueFactory(new PropertyValueFactory<SummaryPojo, String>("fullName"));
        qualificationSummary.setCellValueFactory(new PropertyValueFactory<SummaryPojo, String>("qualificationSummary"));
        professionSummary.setCellValueFactory(new PropertyValueFactory<SummaryPojo, String>("professionSummary"));
        expectedSalary.setCellValueFactory(new PropertyValueFactory<SummaryPojo, String>("expectedSalary"));
        datePublicSummary.setCellValueFactory(new PropertyValueFactory<SummaryPojo, String>("datePublicSummary"));
        /*
        =====================================================
         */
        id_contracts.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("id_contracts"));
        date_contracts.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("date_contracts"));
        name_Employers.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("name_Employers"));
        name_candidates.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("name_candidates"));
        position_name.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("position_name"));
        feddback.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("feddback"));
        agent.setCellValueFactory(new PropertyValueFactory<ContractPojo, String>("agent"));


    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() throws SQLException {
        String company = comboBoxCompany.getValue().toString();
        System.out.println("CBOX" + company);
        // DataBaseConnector.getVacancy();
        for (int i = 0; i < DataBaseConnector.getVacancyOneCompany(company).size(); i++) {
            vacancyData.add(DataBaseConnector.getVacancyOneCompany(comboBoxCompany.getValue().toString()).get(i));
        }

    }

    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();

    private void setComboBoxItems() throws SQLException {

        comboBoxCompany.getItems().setAll(DataBaseConnector.getNameEmployers());


    }


    public void button_SerchVacancy(ActionEvent actionEvent) throws SQLException {
        clearTable();
        initData();
        tableVacancy.setItems(vacancyData);
    }


    private void clearTable() {
        for (int i = 0; i < tableVacancy.getItems().size(); i++) {
            tableVacancy.getItems().clear();
        }
    }

    /*
                        SUMMARRY
    =============================================================================================================
     */
    private void clearTableSummary() {
        for (int i = 0; i < table_Summary.getItems().size(); i++) {
            table_Summary.getItems().clear();
        }
    }

    public void setComboBox_KindUnity() throws SQLException {
        comboBox_KindUnity.getItems().setAll(DataBaseConnector.getKindOfActivity());
    }

    private ObservableList<SummaryPojo> summaryData = FXCollections.observableArrayList();

    public void button_SerchSummary(ActionEvent actionEvent) {
        clearTableSummary();
        String kind[] = comboBox_KindUnity.getValue().toString().split("#");
        System.out.println("CBOX" + kind);
        // DataBaseConnector.getVacancy();
        for (int i = 0; i < DataBaseConnector.getSummaryOnKind(kind[1]).size(); i++) {
            summaryData.add(DataBaseConnector.getSummaryOnKind(kind[1]).get(i));
        }
        table_Summary.setItems(summaryData);
    }

    /*
                        CONTRACTS
    =======================================================
     */
    private void clearTableContracts() {
        for (int i = 0; i < table_contracts.getItems().size(); i++) {
            table_contracts.getItems().clear();
        }
    }

    private ObservableList<ContractPojo> contractData = FXCollections.observableArrayList();


    public void button_refreshTable(ActionEvent actionEvent) {
        clearTableContracts();
        /*for (int i = 0; i < DataBaseConnector.getAllContracts().size(); i++) {
            System.out.println(DataBaseConnector.getAllContracts().get(i).toString());
            contractData.add(DataBaseConnector.getAllContracts().get(i));
        }*/
        //int i =0
        for (ContractPojo cont : DataBaseConnector.getAllContracts()) {
            contractData.add(cont);

        }
        table_contracts.setItems(contractData);

    }

    public void callJobSearch(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("JobSearchWindow.fxml"));
            Stage stg = new Stage();
            stg.setScene(new Scene(root, 800, 600));
            stg.show();
            Main.homeStage.close();
            DataBaseConnector.startConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void callBueroIncome(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Звіт");
        alert.setHeaderText("Прибуток на дату");
        alert.setContentText("Дата: " + DataBaseConnector.getBueroIncome()[1] + '\n'
                + "Прибуток: " + DataBaseConnector.getBueroIncome()[0]);
        alert.show();
    }

    public void callAddCandidates(ActionEvent actionEvent) {
        //String nameC; String surmame; String patr;
        TextInputDialog dialog1 = new TextInputDialog("");
        dialog1.setTitle("Додати кандидата");
        dialog1.setHeaderText("Введіть дані кандидата");
        dialog1.setContentText("Введіть ім'я:");
        Optional<String> name = dialog1.showAndWait();
        //
        TextInputDialog dialog2 = new TextInputDialog("");
        dialog2.setTitle("Додати кандидата");
        dialog2.setHeaderText("Введіть дані кандидата");
        dialog2.setContentText("Введіть прізвище:");
        Optional<String> surmame = dialog2.showAndWait();
        //
        TextInputDialog dialog3 = new TextInputDialog("");
        dialog3.setTitle("Додати кандидата");
        dialog3.setHeaderText("Введіть дані кандидата");
        dialog3.setContentText("Введіть по батькові:");
        Optional<String> patr = dialog3.showAndWait();
        try {
            DataBaseConnector.insertIntoCandidates(name.get(), surmame.get(), patr.get());
        } catch (Exception e) {
            System.err.println("Не вдалось вставити");

        }
    }

    public void callAddEmployers(ActionEvent actionEvent) throws SQLException {
        TextInputDialog dialog1 = new TextInputDialog("");
        dialog1.setTitle("Додати роботодавця");
        dialog1.setHeaderText("Введіть дані");
        dialog1.setContentText("Назва:");
        Optional<String> name = dialog1.showAndWait();

        //
        ArrayList<String> choices = new ArrayList<>();
        choices = DataBaseConnector.getKindOfActivity();
        ChoiceDialog<String> dialog = new ChoiceDialog<String>(choices.get(1), choices);
        dialog.setTitle("Додати роботодавця");
        dialog.setHeaderText("Введіть дані");
        dialog.setContentText("Вид діяльності");
        Optional<String> kind = dialog.showAndWait();
        String kindmass[] = kind.get().toString().split("#");
        //
        TextInputDialog dialog3 = new TextInputDialog("");
        dialog3.setTitle("Додати роботодавця");
        dialog3.setHeaderText("Введіть дані");
        dialog3.setContentText("Адресу:");
        Optional<String> addres = dialog3.showAndWait();
        //
        TextInputDialog dialog4 = new TextInputDialog("");
        dialog4.setTitle("Додати роботодавця");
        dialog4.setHeaderText("Введіть дані");
        dialog4.setContentText("Контактний телефон:");
        Optional<String> phone = dialog4.showAndWait();
        try {

            DataBaseConnector.insertIntoEmployers(name.get(), kindmass[0], addres.get(), phone.get());
        } catch (Exception e) {
            System.err.println("Не вдалось записати");
        }
    }

    public void callExit(ActionEvent actionEvent) {
        System.exit(1);
    }
}
