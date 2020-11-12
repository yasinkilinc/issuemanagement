package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */

import com.bluetech.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Issue History Data Transfer Object")
public class IssueHistoryDto {

    @ApiModelProperty(required = true, value = "ID")
    private Long id;
    @ApiModelProperty(required = true, value = "Issue")
    private IssueDto issue;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(required = true, value = "Date")
    private Date date;
    @ApiModelProperty(required = true, value = "Status Of Isssue")
    private IssueStatus issueStatus;
    @ApiModelProperty(value = "details")
    private String details;
    @ApiModelProperty(required = true, value = "Assignee")
    private UserDto assignee;
}
