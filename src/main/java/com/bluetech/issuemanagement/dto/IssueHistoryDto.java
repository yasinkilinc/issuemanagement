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
@Schema(description = "Issue History Data Transfer Object")
public class IssueHistoryDto {

    @Schema(required = true, description = "ID")
    private Long id;
    @Schema(required = true, description = "Issue")
    private IssueDto issue;
    @Schema(description = "description")
    private String description;
    @Schema(required = true, description = "Date")
    private Date date;
    @Schema(required = true, description = "Status Of Isssue")
    private IssueStatus issueStatus;
    @Schema(description = "details")
    private String details;
    @Schema(required = true, description = "Assignee")
    private UserDto assignee;
}
