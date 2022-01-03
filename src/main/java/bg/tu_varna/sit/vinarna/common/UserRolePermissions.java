package bg.tu_varna.sit.vinarna.common;

import bg.tu_varna.sit.vinarna.business.RoleService;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class UserRolePermissions {

    public static class Permission {
        private RoleModel role;

        // Users
        private boolean usersMenu;
        private boolean usersAddEdit;

        // GrapeSorts
        private boolean grapeSortsMenu;
        private boolean grapeSortsAdd;
        private boolean grapeSortsAddQuantity;

        // WineTypes
        private boolean wineTypesMenu;
        private boolean wineTypeAddEdit;
        private boolean wineTypeProduce;
        private boolean wineTypeBottling;

        // BottleTypes
        private boolean bottleTypesMenu;
        private boolean bottleTypesAddEdit;
        private boolean bottleTypesAddQuantity;

        // BottledWines
        private boolean bottledWinesMenu;

        // References
        private boolean referencesMenu;

        public RoleModel getRole() {
            return role;
        }

        public void setRole(RoleModel role) {
            this.role = role;
        }

        public boolean isUsersMenu() {
            return usersMenu;
        }

        public void setUsersMenu(boolean usersMenu) {
            this.usersMenu = usersMenu;
        }

        public boolean isUsersAddEdit() {
            return usersAddEdit;
        }

        public void setUsersAddEdit(boolean usersAddEdit) {
            this.usersAddEdit = usersAddEdit;
        }

        public boolean isGrapeSortsMenu() {
            return grapeSortsMenu;
        }

        public void setGrapeSortsMenu(boolean grapeSortsMenu) {
            this.grapeSortsMenu = grapeSortsMenu;
        }

        public boolean isGrapeSortsAdd() {
            return grapeSortsAdd;
        }

        public void setGrapeSortsAdd(boolean grapeSortsAdd) {
            this.grapeSortsAdd = grapeSortsAdd;
        }

        public boolean isGrapeSortsAddQuantity() {
            return grapeSortsAddQuantity;
        }

        public void setGrapeSortsAddQuantity(boolean grapeSortsAddQuantity) {
            this.grapeSortsAddQuantity = grapeSortsAddQuantity;
        }

        public boolean isWineTypesMenu() {
            return wineTypesMenu;
        }

        public void setWineTypesMenu(boolean wineTypesMenu) {
            this.wineTypesMenu = wineTypesMenu;
        }

        public boolean isWineTypeAddEdit() {
            return wineTypeAddEdit;
        }

        public void setWineTypeAddEdit(boolean wineTypeAddEdit) {
            this.wineTypeAddEdit = wineTypeAddEdit;
        }

        public boolean isWineTypeProduce() {
            return wineTypeProduce;
        }

        public void setWineTypeProduce(boolean wineTypeProduce) {
            this.wineTypeProduce = wineTypeProduce;
        }

        public boolean isWineTypeBottling() {
            return wineTypeBottling;
        }

        public void setWineTypeBottling(boolean wineTypeBottling) {
            this.wineTypeBottling = wineTypeBottling;
        }

        public boolean isBottleTypesMenu() {
            return bottleTypesMenu;
        }

        public void setBottleTypesMenu(boolean bottleTypesMenu) {
            this.bottleTypesMenu = bottleTypesMenu;
        }

        public boolean isBottleTypesAddEdit() {
            return bottleTypesAddEdit;
        }

        public void setBottleTypesAddEdit(boolean bottleTypesAddEdit) {
            this.bottleTypesAddEdit = bottleTypesAddEdit;
        }

        public boolean isBottleTypesAddQuantity() {
            return bottleTypesAddQuantity;
        }

        public void setBottleTypesAddQuantity(boolean bottleTypesAddQuantity) {
            this.bottleTypesAddQuantity = bottleTypesAddQuantity;
        }

        public boolean isBottledWinesMenu() {
            return bottledWinesMenu;
        }

        public void setBottledWinesMenu(boolean bottledWinesMenu) {
            this.bottledWinesMenu = bottledWinesMenu;
        }

        public boolean isReferencesMenu() {
            return referencesMenu;
        }

        public void setReferencesMenu(boolean referencesMenu) {
            this.referencesMenu = referencesMenu;
        }
    }

    public static class Permissions {

        public static List<Permission> getList() {
            RoleService roleService = RoleService.getInstance();
            ObservableList<RoleModel> roles = roleService.getAllRoles();
            List<Permission> list = new LinkedList<>();


            RoleModel adminRole = roles.stream()
                    .filter(r -> r.getId() == 1)
                    .findAny()
                    .orElse(null);

            RoleModel operatorRole = roles.stream()
                    .filter(r -> r.getId() == 2)
                    .findAny()
                    .orElse(null);

            RoleModel wHostRole = roles.stream()
                    .filter(r -> r.getId() == 3)
                    .findAny()
                    .orElse(null);

            if(adminRole != null) {
                Permission adminPerm = new Permission();
                adminPerm.setRole(adminRole);

                adminPerm.setUsersMenu(true);
                adminPerm.setUsersAddEdit(true);

                adminPerm.setGrapeSortsMenu(true);
                adminPerm.setGrapeSortsAdd(true);
                adminPerm.setGrapeSortsAddQuantity(true);

                adminPerm.setWineTypesMenu(true);
                adminPerm.setWineTypeAddEdit(true);
                adminPerm.setWineTypeProduce(true);
                adminPerm.setWineTypeBottling(true);

                adminPerm.setBottleTypesMenu(true);
                adminPerm.setBottleTypesAddEdit(true);
                adminPerm.setBottleTypesAddQuantity(true);

                adminPerm.setBottledWinesMenu(true);
                adminPerm.setReferencesMenu(true);
                list.add(adminPerm);
            }

            if(operatorRole != null) {
                Permission operatorPerm = new Permission();
                operatorPerm.setRole(operatorRole);

                operatorPerm.setUsersMenu(false);
                operatorPerm.setUsersAddEdit(false);

                operatorPerm.setGrapeSortsMenu(true);
                operatorPerm.setGrapeSortsAdd(true);
                operatorPerm.setGrapeSortsAddQuantity(false);

                operatorPerm.setWineTypesMenu(true);
                operatorPerm.setWineTypeAddEdit(true);
                operatorPerm.setWineTypeProduce(true);
                operatorPerm.setWineTypeBottling(false);

                operatorPerm.setBottleTypesMenu(true);
                operatorPerm.setBottleTypesAddEdit(false);
                operatorPerm.setBottleTypesAddQuantity(false);

                operatorPerm.setBottledWinesMenu(true);
                operatorPerm.setReferencesMenu(true);
                list.add(operatorPerm);
            }

            if(wHostRole != null) {
                Permission wHostPerm = new Permission();
                wHostPerm.setRole(wHostRole);

                wHostPerm.setUsersMenu(false);
                wHostPerm.setUsersAddEdit(false);

                wHostPerm.setGrapeSortsMenu(true);
                wHostPerm.setGrapeSortsAdd(false);
                wHostPerm.setGrapeSortsAddQuantity(true);

                wHostPerm.setWineTypesMenu(true);
                wHostPerm.setWineTypeAddEdit(false);
                wHostPerm.setWineTypeProduce(false);
                wHostPerm.setWineTypeBottling(true);

                wHostPerm.setBottleTypesMenu(true);
                wHostPerm.setBottleTypesAddEdit(true);
                wHostPerm.setBottleTypesAddQuantity(true);

                wHostPerm.setBottledWinesMenu(true);
                wHostPerm.setReferencesMenu(true);
                list.add(wHostPerm);
            }

            return list;
        }

    }

}
