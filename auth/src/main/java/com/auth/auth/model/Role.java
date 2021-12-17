package com.auth.auth.model;

import com.auth.auth.common.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements BaseEnumCode<Integer> {
    ROLE_USER(0),
    ROLE_ADMIN(1);
    private final int code;

    @Override
    public Integer getValue() {
        return code;
    }
}
