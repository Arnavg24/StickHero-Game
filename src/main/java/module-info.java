module com.example {
    
    requires javafx.controls;
    requires javafx.fxml;
    //add requires transitive
    requires transitive javafx.graphics;
    requires javafx.media;

    opens com.example to javafx.fxml;
    exports com.example;
}
