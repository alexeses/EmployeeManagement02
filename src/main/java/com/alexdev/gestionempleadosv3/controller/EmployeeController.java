package com.alexdev.gestionempleadosv3.controller;

import com.alexdev.gestionempleadosv3.model.Employee;
import com.alexdev.gestionempleadosv3.model.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EmployeeController {

    public Button searchEmpBtn, deleteEmpBtn, updateEmpBtn;
    @FXML
    private TextField empIdText, newEmailText, nameText, surnameText, emailText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TableView employeeTable;
    @FXML
    private TableColumn<Employee, Integer> empIdColumn;
    @FXML
    private TableColumn<Employee, String> empNameColumn;
    @FXML
    private TableColumn<Employee, String> empLastNameColumn;
    @FXML
    private TableColumn<Employee, String> empEmailColumn;
    @FXML
    private TableColumn<Employee, String> empPhoneNumberColumn;
    @FXML
    private TableColumn<Employee, Date> empHireDateColumn;

    private Executor exec;

    @FXML
    private void initialize () {

        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });


        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        empNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        empLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        empEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        empPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        empHireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
    }

    @FXML
    private void searchEmployee (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());
            populateAndShowEmployee(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Ops! Ha ocurrido un error al buscar el empleado con id " + empIdText.getText());
            throw e;
        }
    }

    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
            populateEmployees(empData);
        } catch (SQLException e){
            System.out.println("Ops! Ha ocurrido un error al buscar los empleados");
            throw e;
        }
    }

    private void fillEmployeeTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Employee>> task = new Task<List<Employee>>(){
            @Override
            public ObservableList<Employee> call() throws Exception{
                return EmployeeDAO.searchEmployees();
            }
        };

        task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> employeeTable.setItems((ObservableList<Employee>) task.getValue()));
        exec.execute(task);
    }

    @FXML
    private void populateEmployee (Employee emp) throws ClassNotFoundException {
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        empData.add(emp);
        employeeTable.setItems(empData);
    }

    @FXML
    private void setEmpInfoToTextArea ( Employee emp) {
        resultArea.setText("Nombre: " + emp.getFirstName() + " " + emp.getLastName() + " Email: " + emp.getEmail());
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
        if (emp != null) {
            populateEmployee(emp);
            setEmpInfoToTextArea(emp);
        } else {
            resultArea.setText("Ops! No se ha encontrado el empleado con id " + empIdText.getText());
        }
    }

    @FXML
    private void populateEmployees (ObservableList<Employee> empData) throws ClassNotFoundException {
        employeeTable.setItems(empData);
    }

    @FXML
    private void updateEmployeeEmail (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.updateEmpEmail(empIdText.getText(),newEmailText.getText());
            resultArea.setText("El email del empleado con id " + empIdText.getText() + " ha sido actualizado");
            fillEmployeeTable(actionEvent);
        } catch (SQLException e) {
            resultArea.setText("Ops! Ha ocurrido un error al actualizar el email del empleado con id " + empIdText.getText());
        }
    }

    @FXML
    private void insertEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.insertEmp(nameText.getText(),surnameText.getText(),emailText.getText());
            resultArea.setText("El empleado ha sido insertado");
            txtClear();
            fillEmployeeTable(actionEvent);
        } catch (SQLException e) {
            resultArea.setText("Ops! Ha ocurrido un error al insertar el empleado");
            throw e;
        }
    }

    private void txtClear() {
        nameText.clear();
        surnameText.clear();
        emailText.clear();
        empIdText.clear();
        newEmailText.clear();
    }

    @FXML
    private void deleteEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmpWithId(empIdText.getText());
            resultArea.setText("El empleado con id " + empIdText.getText() + " ha sido eliminado");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Empleado eliminado");
            alert.setContentText("El empleado con id " + empIdText.getText() + " ha sido eliminado");
            alert.showAndWait();
            txtClear();
            fillEmployeeTable(actionEvent);

        } catch (SQLException e) {
            resultArea.setText("Ops! Ha ocurrido un error al eliminar el empleado con id " + empIdText.getText());
            throw e;
        }
    }
}