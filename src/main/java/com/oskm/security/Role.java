package com.oskm.security;

public enum Role {
    ADMIN("admin"), MEMBER("member"), GUEST("guest");

    private Role(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public static Role find(String name) {
        for (Role each : values()) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return GUEST;
    }
}
