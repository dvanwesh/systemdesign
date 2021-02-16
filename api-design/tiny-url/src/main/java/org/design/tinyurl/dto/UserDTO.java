package org.design.tinyurl.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class UserDTO {
    @NotNull
    private Long userId;
    private String name;
    private DeviceDTO device;

    public UserDTO(@NotNull Long userId) {
        this.userId = userId;
        name = null;
        device = null;
    }
}
