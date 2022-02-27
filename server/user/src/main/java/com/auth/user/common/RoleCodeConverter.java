package com.auth.user.common;


import com.auth.user.model.Role;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleCodeConverter extends AbstractBaseEnumConverter<Role, Integer> {

    @Override
    protected Role[] getValueList() {
        return Role.values();
    }
}