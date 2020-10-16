import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Main.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root;
        root = (AnchorPane) FXMLLoader.load(Main.class.getResource("/views/MainFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Test your reflexes!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
