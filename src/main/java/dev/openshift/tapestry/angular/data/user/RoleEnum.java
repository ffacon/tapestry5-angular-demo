package dev.openshift.tapestry.angular.data.user;



public enum RoleEnum {
    ALL(RoleConsts.ALL_ROLE), ADMIN(RoleConsts.ADMIN_ROLE), USER(RoleConsts.USER_ROLE);

    private String role;

    private RoleEnum(String s) {
        role = s;
    }

    public String getRole() {
        return role;
    }

}
