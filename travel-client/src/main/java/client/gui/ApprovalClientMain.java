package client.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class ApprovalClientMain extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        final Logger logger = LoggerFactory.getLogger(ApprovalClientMain.class.getName());

        URL url  = getClass().getClassLoader().getResource("approval_client.fxml");
        if (url != null) {
            FXMLLoader loader = new FXMLLoader(url);
            ApprovalClientController controller = new ApprovalClientController();
            loader.setController(controller);
            Parent root = loader.load();



            primaryStage.setTitle("Travel Refund Client");

            primaryStage.setOnCloseRequest(t -> {
                logger.info("Closing approval client .....");
                controller.stop();
                Platform.exit();
                System.exit(0);
            });
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/client.png")));
            primaryStage.setScene(new Scene(root, 477, 400));
            primaryStage.show();
        }else {
           logger.error("Could not load frame from approval_client.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
