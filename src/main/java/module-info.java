module SIGLC {
    requires java.sql;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.dotenv;
    requires jbcrypt;
    requires org.postgresql.jdbc;

    exports org.example.siglc.application;

    opens org.example.siglc.controller to javafx.fxml;
}
