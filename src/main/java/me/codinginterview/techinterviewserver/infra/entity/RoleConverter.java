package me.codinginterview.techinterviewserver.infra.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null) {
            return null;
        }
        return role.getKey();
    }

    @Override
    public Role convertToEntityAttribute(String key) {
        if(key == null) {
            return null;
        }

        return Role.valueOf(key);
    }
}
