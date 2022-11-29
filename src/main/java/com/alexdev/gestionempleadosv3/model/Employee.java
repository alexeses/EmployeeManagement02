package com.alexdev.gestionempleadosv3.model;

import javafx.beans.property.*;
import java.sql.Date;

public class Employee {
    private final IntegerProperty idEmple;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty email;
    private final StringProperty telefono;
    private final SimpleObjectProperty<Date> fecha;
    private final StringProperty idTrabajo;
    private final IntegerProperty salario;
    private final DoubleProperty commission_pct;
    private final IntegerProperty manager_id;
    private final IntegerProperty department_id;

    public Employee() {
        this.idEmple = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.apellido = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.fecha = new SimpleObjectProperty<>();
        this.idTrabajo = new SimpleStringProperty();
        this.salario = new SimpleIntegerProperty();
        this.commission_pct = new SimpleDoubleProperty();
        this.manager_id = new SimpleIntegerProperty();
        this.department_id = new SimpleIntegerProperty();
    }

    public int getEmployeeId() {
        return idEmple.get();
    }

    public void setEmployeeId(int employeeId){
        this.idEmple.set(employeeId);
    }

    public IntegerProperty employeeIdProperty(){
        return idEmple;
    }

    public String getFirstName () {
        return nombre.get();
    }

    public void setFirstName(String firstName){
        this.nombre.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return nombre;
    }

    public String getLastName () {
        return apellido.get();
    }

    public void setLastName(String lastName){
        this.apellido.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return apellido;
    }

    public String getEmail () {
        return email.get();
    }

    public void setEmail (String email){
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber () {
        return telefono.get();
    }

    public void setPhoneNumber (String phoneNumber){
        this.telefono.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return telefono;
    }

    public Object getHireDate(){
        return fecha.get();
    }

    public void setHireDate(Date hireDate){
        this.fecha.set(hireDate);
    }

    public SimpleObjectProperty<Date> hireDateProperty(){
        return fecha;
    }

    public String getJobId () {
        return idTrabajo.get();
    }

    public void setJobId (String jobId){
        this.idTrabajo.set(jobId);
    }

    public StringProperty jobIdProperty() {
        return idTrabajo;
    }

    public int getSalario() {
        return salario.get();
    }

    public void setSalario(int salario){
        this.salario.set(salario);
    }

    public IntegerProperty salarioProperty(){
        return salario;
    }

    public double getCommissionPct() {
        return commission_pct.get();
    }

    public void setCommissionPct(double commissionPct){
        this.commission_pct.set(commissionPct);
    }

    public DoubleProperty commissionPctProperty(){
        return commission_pct;
    }

    public int getManagerId() {
        return manager_id.get();
    }

    public void setManagerId(int managerId){
        this.manager_id.set(managerId);
    }

    public IntegerProperty managerIdProperty(){
        return manager_id;
    }

    public int getDepartmanId() {
        return department_id.get();
    }

    public void setDepartmantId(int departmentId){
        this.manager_id.set(departmentId);
    }

    public IntegerProperty departmentIdProperty(){
        return department_id;
    }
}