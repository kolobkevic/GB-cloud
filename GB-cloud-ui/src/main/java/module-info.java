module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.buffer;
    requires java.logging;

    opens org.kolobkevic.GB_cloud.ui to javafx.fxml;
    exports org.kolobkevic.GB_cloud.ui;
}