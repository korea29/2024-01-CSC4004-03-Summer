package com.dgu_csc.akomanager_back.dto;

import com.dgu_csc.akomanager_back.model.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private Users updatedUsers;
    private String password;
}
