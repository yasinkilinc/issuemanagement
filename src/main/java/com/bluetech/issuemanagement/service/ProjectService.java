package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.ProjectDto;
import com.bluetech.issuemanagement.entity.Project;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDto save(ProjectDto issue);

    ProjectDto getById(Long id);

    Project getByProjectCode(String projectCode);

    List<Project> getByProjectCodeContains(String projectCode);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    Boolean delete(Project project);

    ProjectDto update(Long id, ProjectDto project);

}
