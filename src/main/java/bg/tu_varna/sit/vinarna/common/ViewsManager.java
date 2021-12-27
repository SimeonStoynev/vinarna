package bg.tu_varna.sit.vinarna.common;

import bg.tu_varna.sit.vinarna.presentation.controllers.DashboardController;
import bg.tu_varna.sit.vinarna.presentation.controllers.LoginController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewsManager {
    private static final Logger log = Logger.getLogger(ViewsManager.class);

    public static class MenuItem {
        public String label;
        public FontAwesomeIcon icon;
        public int iconType;
        public String imageDir;
        public String viewDir;
        public Class viewClass;

        public MenuItem(String label, FontAwesomeIcon icon, int iconType, String imageDir, String viewDir, Class viewClass) {
            this.label = label;
            this.icon = icon;
            this.iconType = iconType;
            this.imageDir = imageDir;
            this.viewDir = viewDir;
            this.viewClass = viewClass;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public FontAwesomeIcon getIcon() {
            return icon;
        }

        public void setIcon(FontAwesomeIcon icon) {
            this.icon = icon;
        }

        public int getIconType() {
            return iconType;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }

        public String getImageDir() {
            return imageDir;
        }

        public void setImageDir(String imageDir) {
            this.imageDir = imageDir;
        }

        public String getViewDir() {
            return viewDir;
        }

        public void setViewDir(String viewDir) {
            this.viewDir = viewDir;
        }

        public Class getViewClass() {
            return viewClass;
        }

        public void setViewClass(Class viewClass) {
            this.viewClass = viewClass;
        }
    }

    public static void changeView(String title, String viewDir, Class cl, Stage... st) throws IOException {
        if(st.length > 0)
            closeView(st[0]);
        Stage stage = new Stage();
        PropertyConfigurator.configure(cl.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        URL path = LoginController.class.getResource(viewDir);

        if (path != null) {
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(Objects.requireNonNull(LoginController.class.getResourceAsStream(Constants.Media.APP_ICON))));
            stage.setScene(scene);

            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(1);
            });

            stage.setTitle(title);
            stage.setResizable(true);
            stage.setMinWidth(918);
            stage.setMinHeight(600);
            if(st.length > 0) {
                stage.setWidth(st[0].getWidth());
                stage.setHeight(st[0].getHeight());
                stage.setMaximized(st[0].isMaximized());
            } else {
                stage.setWidth(918);
                stage.setHeight(600);
            }

            stage.show();
        } else {
            log.error("View couldn't be loaded: " + viewDir);
            System.exit(-1);
        }
    }

    public static void leftMenuGenerate(AnchorPane pane, Class viewClass) {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Dashboard", FontAwesomeIcon.HOME, 0, "",
                Constants.View.DASHBOARD_VIEW, DashboardController.class));

        menuItems.add(new MenuItem("Users", FontAwesomeIcon.USERS, 0, "",
                Constants.View.LOGIN_VIEW, LoginController.class));

        menuItems.add(new MenuItem("Grape", FontAwesomeIcon.USER, 1, Constants.Media.LEFTMENU_GRAPE,
                Constants.View.LOGIN_VIEW, LoginController.class));

        menuItems.add(new MenuItem("Wine recipes", FontAwesomeIcon.USER, 1, Constants.Media.LEFTMENU_BOOK,
                Constants.View.LOGIN_VIEW, LoginController.class));

        int y = 0;
        pane.getChildren().remove(0);
        for (MenuItem item : menuItems) {
            Button button = new Button("");
            HBox hbox = new HBox();

            FontAwesomeIconView icon = new FontAwesomeIconView(item.getIcon(), "30");
            ImageView image = new ImageView();
            Label label = new Label(item.getLabel());

            label.setPadding(new Insets(0, 0, 0, 10));
            icon.setTextAlignment(TextAlignment.CENTER);
            icon.setWrappingWidth(42);

            hbox.setPrefWidth(213);
            hbox.setPrefHeight(32);
            hbox.setLayoutX(0);
            hbox.setLayoutY(0);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(0, 0, 0, 10));

            if (item.getIconType() == 1) {
                image.setPreserveRatio(true);
                image.setFitWidth(50);
                image.setFitHeight(28);

                image.setSmooth(true);
                image.setImage(new Image(Objects.requireNonNull(LoginController.class.getResourceAsStream(item.getImageDir()))));
                hbox.getChildren().add(image);
            } else {
                hbox.getChildren().add(icon);
            }
            hbox.getChildren().add(label);

            button.setId(item.getLabel());
            button.setGraphic(hbox);

            button.setLayoutY(y);
            button.setPrefWidth(233);
            button.setPrefHeight(50);
            button.getStyleClass().add("leftMenuButton");

            if (item.getViewClass().equals(viewClass)) {
                button.getStyleClass().add("selected");
            } else {
                button.setOnAction(event -> {
                    try {
                        leftMenuItemPressEvent(button, item);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            pane.getChildren().add(button);
            y += 55;
        }
    }

    private static void leftMenuItemPressEvent(Button button, MenuItem item) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        changeView(item.getLabel(), item.getViewDir(), item.getViewClass(), stage);
    }

    public static void closeView(Stage st) {
        st.close();
    }

}
