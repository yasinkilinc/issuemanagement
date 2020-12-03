package com.bluetech.issuemanagement.api;

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.dto.UserDto;
import com.bluetech.issuemanagement.service.impl.UserServiceImpl;
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
import java.util.List;

/*
 * Created by yasinkilinc on 10.11.2020
 */
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Tag(name = "UserController", description = "User APIs")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @Operation(summary = "Get All By Pagination Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TPage.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable){
        TPage<UserDto> allPageable = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(allPageable);
    }

    @GetMapping
    @Operation(summary = "Get All Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TPage.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> allPageable = userServiceImpl.getAll();
        return ResponseEntity.ok(allPageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id){
        UserDto userDto = userServiceImpl.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/{id}", consumes = "application/vnd.bluetech.v2+json", produces = "application/vnd.bluetech.v2+json")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<UserDto> getByIdV2(@PathVariable("id") Long id){
        UserDto userDto = userServiceImpl.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @Operation(summary = "Create Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        return ResponseEntity.ok(userServiceImpl.save(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,  @Valid @RequestBody UserDto user){
        return ResponseEntity.ok(userServiceImpl.update(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
       return ResponseEntity.ok(userServiceImpl.delete(id));
    }

}
