package com.bluetech.issuemanagement.api;

import com.bluetech.issuemanagement.dto.IssueDetailDto;
import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.service.impl.IssueServiceImpl;
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
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Tag(name = "IssueController", description = "Issue APIs")
@CrossOrigin
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("/pagination")
    @Operation(summary = "Get All By Pagination Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TPage.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Issue",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue not found",
                    content = @Content) })
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable){
        TPage<IssueDto> allPageable = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(allPageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Issue",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Issue",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue not found",
                    content = @Content) })
    public ResponseEntity<IssueDto> getById(@PathVariable("id") Long id){
        IssueDto projectDto = issueServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Issue",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Issue",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue not found",
                    content = @Content) })
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id) {
        IssueDetailDto detailDto = issueServiceImpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }

    @PostMapping
    @Operation(summary = "Create Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Issue",
                    content = @Content)})
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issueDto){
        return ResponseEntity.ok(issueServiceImpl.save(issueDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id Issue",
                    content = @Content)})
    public ResponseEntity<IssueDto> updateIssue(@PathVariable("id") Long id,  @Valid @RequestBody IssueDto issueDto){
        return ResponseEntity.ok(issueServiceImpl.update(id, issueDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Issue",
                    content = @Content)})
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
       return ResponseEntity.ok(issueServiceImpl.delete(id));
    }

}
