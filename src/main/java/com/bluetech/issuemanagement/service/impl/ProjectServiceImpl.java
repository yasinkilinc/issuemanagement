package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.ProjectDto;
import com.bluetech.issuemanagement.entity.Project;
import com.bluetech.issuemanagement.repository.ProjectRepository;
import com.bluetech.issuemanagement.service.ProjectService;
import com.bluetech.issuemanagement.util.TPage;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project projectCheck = projectRepository.getByProjectCode(projectDto.getProjectCode());

        if(projectCheck != null ){
            throw new IllegalArgumentException("Project code already exists");
        }

        Project project = modelMapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        projectDto.setId(project.getId());
        return projectDto;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project project = projectRepository.getOne(id);
        return modelMapper.map( project, ProjectDto.class);
    }

    @Override
    public Project getByProjectCode(String projectCode) {
        return projectRepository.getByProjectCode(projectCode);
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return projectRepository.getByProjectCodeContains(projectCode);
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        return new TPage<ProjectDto>( data ,Arrays.asList( modelMapper.map( data.getContent(),ProjectDto[].class)));
    }

    @Override
    public Boolean delete(Project project) {
        projectRepository.delete(project);
        return Boolean.TRUE;
    }

    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project projectDb = projectRepository.getOne(id);

        if(projectDb == null ){
            throw new IllegalArgumentException("Project does not exist id: " + id);
        }

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(projectDto.getProjectCode(), id);
        if(projectCheck != null){
            throw new IllegalArgumentException("Project code already exists");
        }
        projectDb.setProjectCode( projectDto.getProjectCode() );
        projectDb.setProjectName( projectDto.getProjectName() );

        return modelMapper.map( projectRepository.save(projectDb), ProjectDto.class);
    }
}
