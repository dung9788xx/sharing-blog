package com.nhom2.sharingblog.enums;

public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    private final String role;
    Roles(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return role;
    }
}
