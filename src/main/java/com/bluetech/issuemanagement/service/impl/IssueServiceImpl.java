package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.entity.Issue;
import com.bluetech.issuemanagement.repository.IssueRepository;
import com.bluetech.issuemanagement.service.IssueService;
import com.bluetech.issuemanagement.util.TPage;
import java.util.Arrays;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository,
                            ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDto save(IssueDto issue) {
        if(issue.getDate() == null){
            throw new IllegalArgumentException("Issue date can not be null");
        }
        Issue issueDb = modelMapper.map(issue, Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb, IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map( issue, IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        IssueDto[] dtos = modelMapper.map(data.getContent(), IssueDto[].class);
        return new TPage<IssueDto>(data, Arrays.asList(dtos));
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {
        Issue issueDb = issueRepository.getOne(id);

        if(issueDb == null ){
            throw new IllegalArgumentException("Issue does not exist id: " + id);
        }

//        Project projectCheck = issueRepository.getById(projectDto.getProjectCode(), id);
//        if(projectCheck != null){
//            throw new IllegalArgumentException("Project code already exists");
//        }
//        issueDb.setProjectCode( projectDto.getProjectCode() );
//        issueDb.setProjectName( projectDto.getProjectName() );

//        return modelMapper.map( projectRepository.save(projectDb), ProjectDto.class);
        return null;
    }
}
