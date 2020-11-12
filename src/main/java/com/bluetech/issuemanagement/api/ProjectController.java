package com.bluetech.issuemanagement.api;

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.dto.ProjectDto;
import com.bluetech.issuemanagement.service.impl.ProjectServiceImpl;
import com.bluetech.issuemanagement.util.ApiPaths;
import com.bluetech.issuemanagement.util.TPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * Created by yasinkilinc on 10.11.2020
 */
@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Tag(name = "ProjectController", description = "Project APIs")
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/pagination")
    @Operation(summary = "Get By Id Pagination Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TPage.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable){
        TPage<ProjectDto> allPageable = projectServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(allPageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id){
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping(value = "/{id}", consumes = "application/vnd.bluetech.v2+json", produces = "application/vnd.bluetech.v2+json")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<ProjectDto> getByIdV2(@PathVariable("id") Long id){
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @Operation(summary = "Create Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project){
        return ResponseEntity.ok(projectServiceImpl.save(project));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id,  @Valid @RequestBody ProjectDto project){
        return ResponseEntity.ok(projectServiceImpl.update(id, project));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content) })
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
       return ResponseEntity.ok(projectServiceImpl.delete(id));
    }

}
