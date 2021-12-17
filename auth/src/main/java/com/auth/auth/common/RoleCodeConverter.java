package com.auth.auth.common;


import com.auth.auth.model.Role;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleCodeConverter extends AbstractBaseEnumConverter<Role, Integer> {

    @Override
    protected Role[] getValueList() {
        return Role.values();
    }
}