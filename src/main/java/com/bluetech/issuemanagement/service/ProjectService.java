package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.ProjectDto;
import com.bluetech.issuemanagement.entity.Project;
import com.bluetech.issuemanagement.util.TPage;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Project save(Project issue);

    Project getById(Long id);

    List<Project> getByProjectCode(String projectCode);

    List<Project> getByProjectCodeContains(String projectCode);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    Boolean delete(Project project);

}
