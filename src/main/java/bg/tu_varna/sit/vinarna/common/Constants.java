package bg.tu_varna.sit.vinarna.common;

public class Constants {
    public static class View {
        public static final String HELLO_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/hello-view.fxml";
        public static final String LOGIN_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/LoginView/LoginView.fxml";
        public static final String DASHBOARD_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/CommonViews/DashboardView.fxml";
        public static final String USERSANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Users/UsersAnchorPane.fxml";
        public static final String USERSADDEDITDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Users/UsersAddEditDialog.fxml";
        public static final String GRAPESORTSANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeSortsAnchorPane.fxml";
        public static final String GRAOESORTSADDEDITDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeSortsAddEditDialog.fxml";
        public static final String GRAPEQUANTITYADDDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeQuantityAddDialog.fxml";
        public static final String WINETYPESANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Wines/WineTypesAnchorPane.fxml";
        public static final String WINETYPESTABLEROW_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Wines/WineTypesTableRow.fxml";
        public static final String WINETYPESADDEDITDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Wines/WineTypesAddEditDialog.fxml";
        public static final String WINETYPESGRAPECHOSEROW_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Wines/WineTypeGrapeChoseRow.fxml";
        public static final String WINETYPESPRODUCEDIALOG_VIEW ="/bg/tu_varna/sit/vinarna/presentation/views/Wines/WineTypesProduceDialog.fxml";
        public static final String BOTTLETYPESANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Bottles/BottleTypesAnchorPane.fxml";
        public static final String BOTTLETYPESTABLEROW_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Bottles/BottleTypesTableRow.fxml";
        public static final String BOTTLETYPESADDEDITDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Bottles/BottleTypesAddEditDialog.fxml";
        public static final String BOTTLEQUANTITYADDDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/Bottles/BottleQuantityAddDialog.fxml";
        public static final String BOTTLEDWINESANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/BottledWines/BottledWinesAnchorPane.fxml";
        public static final String BOTTLINGWINEDIALOG_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/BottledWines/BottlingWineDialog.fxml";
        public static final String BOTTLEDWINESROW_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/BottledWines/BottledWinesRow.fxml";
        public static final String REFERENCESANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/References/ReferencesAnchorPane.fxml";
        public static final String REFERENCEGRAPEANCHORPANE_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/References/ReferencesGrapeAnchorPane.fxml";
        public static final String REFERENCEGRAPESTORAGEROW_VIEW = "/bg/tu_varna/sit/vinarna/presentation/views/References/ReferencesGrapeStorageRow.fxml";
    }

    public static class Configurations {
        public static final String LOG4J_PROPERTIES = "/bg/tu_varna/sit/vinarna/configuration/log4j.properties";
    }

    public static class Values {
        public static final String TITLE = "Vinarna";
        public static final String SUCCESS_COLOR = "#2B8C0BFF";
        public static final String ERROR_COLOR = "#8C0B31FF";
    }

    public static class Media {
        public static final String APP_ICON = "/bg/tu_varna/sit/vinarna/presentation/media/icon.png";
        public static final String LEFTMENU_GRAPE = "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_grape.png";
        public static final String LEFTMENU_BOOK = "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_book.png";
        public static final String LEFTMENU_BOTLES = "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_botles.png";
        public static final String LEFTMENU_WINEBOTTLE = "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_wineBottle.png";
        public static final String LEFTMENU_REFERENCES = "/bg/tu_varna/sit/vinarna/presentation/media/leftMenu_refference.png";
    }

    public static class Minima {
        public static final Double GRAPE_MINIMUM = 50.00;
        public static final int BOTTLES_MINIMUM = 100;
    }
}
