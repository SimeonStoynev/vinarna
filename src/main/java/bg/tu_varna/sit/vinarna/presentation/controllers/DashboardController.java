package bg.tu_varna.sit.vinarna.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.apache.log4j.Logger;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DashboardController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @FXML
    AnchorPane menuPane;

    @FXML
    private void initialize() {
        System.out.println("test");
        menuBuild();
    }

    public class MenuItem {
        public String label;
        public FontAwesomeIcon icon;
        public int iconType;
        public String imageDir;

        public MenuItem(String label, FontAwesomeIcon icon, int iconType, String imageDir) {
            this.label = label;
            this.icon = icon;
            this.iconType = iconType;
            this.imageDir = imageDir;
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
    }

    private void menuBuild() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Dashboard", FontAwesomeIcon.HOME, 0, ""));
        items.add(new MenuItem("Users", FontAwesomeIcon.USERS, 0, ""));
        items.add(new MenuItem("Grape", FontAwesomeIcon.USER, 1, "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_grape.png"));
        items.add(new MenuItem("Wine recipes", FontAwesomeIcon.USER, 1, "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_book.png"));

        int y = 0;
        menuPane.getChildren().remove(0);
        for (MenuItem item : items) {
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

            if(item.getIconType() == 1) {
                image.setPreserveRatio(true);
                image.setFitWidth(50);
                image.setFitHeight(28);

                image.setSmooth(true);
                image.setImage(new Image(LoginController.class.getResourceAsStream(item.getImageDir())));
                hbox.getChildren().add(image);
            } else {
                hbox.getChildren().add(icon);
            }
            hbox.getChildren().add(label);

            button.setGraphic(hbox);

            button.setLayoutY(y);
            button.setPrefWidth(233);
            button.setPrefHeight(50);
            button.getStyleClass().add("leftMenuButton");
            if(item.getLabel().equals("Dashboard"))
                button.getStyleClass().add("selected");
            menuPane.getChildren().add(button);
            y += 55;
        }



    }

}
