module com.alexdev.gestionempleadosv3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens com.alexdev.gestionempleadosv3 to javafx.fxml;
    exports com.alexdev.gestionempleadosv3;

    opens com.alexdev.gestionempleadosv3.controller to javafx.fxml;
    exports com.alexdev.gestionempleadosv3.controller;

}