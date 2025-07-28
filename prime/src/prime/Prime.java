package prime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Prime extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file and create scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("prime_number_sieve.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Set title and show stage
        primaryStage.setTitle("Sieve of Eratosthenes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method for non-Maven execution
    public static void main(String[] args) {
        launch(args);
    }
}