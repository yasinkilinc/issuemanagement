package com.bluetech.issuemanagement.dto;

import lombok.Data;

/**
 * Created by yasinkilinc on 12.12.2020
 */
@Data
public class RegistrationRequest {
    private String nameSurname;
    private String username;
    private String password;
    private String email;
}

