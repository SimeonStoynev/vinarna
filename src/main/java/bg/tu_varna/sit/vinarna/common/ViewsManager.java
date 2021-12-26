package bg.tu_varna.sit.vinarna.common;

import bg.tu_varna.sit.vinarna.application.HelloApplication;
import bg.tu_varna.sit.vinarna.presentation.controllers.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class ViewsManager {
    private static final Logger log = Logger.getLogger(ViewsManager.class);

    public static void changeView(String viewDir, Class cl, Stage... st) throws IOException {
        if(st.length > 0)
            closeView(st[0]);
        Stage stage = new Stage();
        PropertyConfigurator.configure(cl.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        URL path = LoginController.class.getResource(viewDir);

        if (path != null) {
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(LoginController.class.getResourceAsStream("/bg/tu_varna/sit/vinarna/presentation/media/icon.png")));
            stage.setScene(scene);

            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(1);
            });

            if(st.length > 0) {
                stage.setMinWidth(st[0].getMinWidth());
                stage.setMinHeight(st[0].getMinHeight());
                stage.setWidth(st[0].getWidth());
                stage.setHeight(st[0].getHeight());
                stage.setMaximized(st[0].isMaximized());
            }

            stage.show();
        } else {
            log.error("View couldn't be loaded: " + viewDir);
            System.exit(-1);

        }
    }

    public static void closeView(Stage st) {
        st.close();
    }

}
