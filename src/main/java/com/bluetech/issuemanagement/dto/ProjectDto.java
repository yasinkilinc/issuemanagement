package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Project Data Transfer Object")
public class ProjectDto {

    @Schema(required = true, description = "Project ID")
    private Long id;

    @NotNull
    @Schema(required = true, description = "Name of Project")
    private String projectName;

    @NotNull
    @Schema(required = true, description = "Code of Project")
    private String projectCode;

}
