package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.common.UserSession;
import bg.tu_varna.sit.vinarna.presentation.controllers.LoginController;
import bg.tu_varna.sit.vinarna.presentation.controllers.NotificationRowController;
import bg.tu_varna.sit.vinarna.presentation.models.NotificationModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DashboardService {
    private static final Logger log = Logger.getLogger(LoginController.class);

    public static DashboardService getInstance() {
        return DashboardService.DashboardServiceHolder.INSTANCE;
    }

    private static class DashboardServiceHolder {
        public static final DashboardService INSTANCE = new DashboardService();
    }

    public class BackgroundWorker extends Thread {

        NotificationService notificationService = NotificationService.getInstance();

        AnchorPane notificationRows;
        AnchorPane norifCountAnchorPane;
        Label notifCountLabel;

        int unseenNotifications;
        int unseenNotificationsOld;
        ObservableList<NotificationModel> notifications;

        public BackgroundWorker(AnchorPane notificationRows, AnchorPane norifCountAnchorPane, Label notifCountLabel) {
            this.notificationRows = notificationRows;
            this.norifCountAnchorPane = norifCountAnchorPane;
            this.notifCountLabel = notifCountLabel;
            this.unseenNotifications = 0;
            this.unseenNotificationsOld = 0;

            notifications = notificationService.getDescByUser(UserSession.user, 500);
            notificationsUiUpdate();
        }

        public void run(){
            try {

                while(true) {
                    int unseen = notificationService.getUnseenCountByUser(UserSession.user);

                    if(unseen != unseenNotifications) {
                        notifications = notificationService.getDescByUser(UserSession.user, 200);

                        unseenNotificationsOld = unseenNotifications;
                        unseenNotifications = unseen;
                        notificationsUiUpdate();
                    }

                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                log.error("Background Thread error: " + e.getMessage());
            }

        }

        public void notificationsUiUpdate() {
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        notifCountLabel.setText(String.valueOf(unseenNotifications));
                        if(unseenNotifications == 0 && norifCountAnchorPane.isVisible())
                            norifCountAnchorPane.setVisible(false);
                        else if(unseenNotifications > 0 && !norifCountAnchorPane.isVisible())
                            norifCountAnchorPane.setVisible(true);

                        notificationRows.getChildren().clear();

                        int y = 0;
                        for(NotificationModel notif : notifications) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.NORIFICATIONROW_VIEW));
                            AnchorPane notifRow = loader.load();
                            NotificationRowController controller = loader.getController();

                            String pattern = "dd MMM yyyy - HH:mm";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("us", "US"));
                            controller.timeLabel.setText(simpleDateFormat.format(notif.getCreated_at()));
                            controller.textLabel.setText(notif.getText());


                            AnchorPane.setRightAnchor(notifRow, 0.0);
                            AnchorPane.setLeftAnchor(notifRow, 0.0);
                            notifRow.setLayoutY(y);

                            notificationRows.getChildren().add(notifRow);

                            y+=75;
                        }

                        if(unseenNotificationsOld < unseenNotifications) {
                            try {
                                String musicFile = "src/main/resources/bg/tu_varna/sit/vinarna/presentation/media/notificationSound.wav";

                                Media sound = new Media(new File(musicFile).toURI().toString());
                                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                                mediaPlayer.play();
                                Thread.sleep(1300);
                            } catch (Exception ex) {
                                log.error("Playing notification sound error: " + ex.getMessage());
                            }
                        }

                    } catch (Exception ex) {
                        log.error("Notifications row update error: " + ex.getMessage());
                    }

                }
            });
        }
    }

    public void backgroundWorkerRun(AnchorPane notificationAnchorPane, AnchorPane norifCountAnchorPane, Label notifCountLabel) {
        BackgroundWorker thread = new BackgroundWorker(notificationAnchorPane, norifCountAnchorPane, notifCountLabel);
        thread.start();
    }
}
