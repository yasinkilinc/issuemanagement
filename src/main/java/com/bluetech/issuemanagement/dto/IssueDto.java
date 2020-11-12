package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */

import com.bluetech.issuemanagement.entity.IssueStatus;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Issue Data Transfer Object")
public class IssueDto {

    @ApiModelProperty(required = true, value = "ID")
    private Long id;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "details")
    private String details;
    @ApiModelProperty(required = true, value = "Date of Issue")
    private Date date;
    @ApiModelProperty(required = true, value = "Status Of Isssue")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true, value = "Assignee")
    private UserDto assignee;
    @ApiModelProperty(required = true, value = "Issue Project")
    private ProjectDto project;

}
