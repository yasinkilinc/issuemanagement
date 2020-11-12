package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Data Transfer Object")
public class UserDto {
    private Long id;
    private String nameSurname;

}
