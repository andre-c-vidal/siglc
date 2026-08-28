module SIGLC {
    requires java.naming;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires jakarta.persistence;
    requires java.dotenv;
    requires jbcrypt;
    requires org.postgresql.jdbc;
    requires org.hibernate.orm.core;

    opens org.example.siglc.controller to javafx.fxml;
    opens org.example.siglc.model.entity to org.hibernate.orm.core;

    exports org.example.siglc;
}
