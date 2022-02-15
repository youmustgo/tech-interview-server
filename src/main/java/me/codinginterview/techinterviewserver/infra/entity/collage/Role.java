package me.codinginterview.techinterviewserver.infra.entity.collage;

public enum Role {
    USER("USER"),
    POWER_USER("POWER_USER");

    private String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
