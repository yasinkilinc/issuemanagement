package com.bluetech.issuemanagement.dto;
/*
 * Created by yasinkilinc on 5.11.2020
 */

import com.bluetech.issuemanagement.entity.IssueStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Issue Update Data Transfer Object")
public class IssueUpdateDto {

    @Schema(required = true, description = "ID")
    private Long id;
    @Schema(description = "description")
    private String description;
    @Schema(description = "details")
    private String details;
    @Schema(required = true, description = "Date of Issue")
    private Date date;
    @Schema(required = true, description = "Status Of Isssue")
    private IssueStatus issueStatus;
    @Schema(required = true, description = "Assignee")
    private Long assignee_id;
    @Schema(required = true, description = "Project")
    private Long project_id;

}
