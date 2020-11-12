package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Project Data Transfer Object")
public class ProjectDto {

    @ApiModelProperty(required = true, value = "Project ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true, value = "Name of Project")
    private String projectName;

    @NotNull
    @ApiModelProperty(required = true, value = "Code of Project")
    private String projectCode;

}
