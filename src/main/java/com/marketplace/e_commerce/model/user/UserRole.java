package com.marketplace.e_commerce.model.user;

public enum UserRole {
    ADMIN("admin"),

    USER("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }




}
