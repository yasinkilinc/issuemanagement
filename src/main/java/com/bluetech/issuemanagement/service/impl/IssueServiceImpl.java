package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.IssueDetailDto;
import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.dto.IssueHistoryDto;
import com.bluetech.issuemanagement.dto.IssueUpdateDto;
import com.bluetech.issuemanagement.entity.Issue;
import com.bluetech.issuemanagement.entity.IssueStatus;
import com.bluetech.issuemanagement.entity.User;
import com.bluetech.issuemanagement.repository.IssueRepository;
import com.bluetech.issuemanagement.repository.ProjectRepository;
import com.bluetech.issuemanagement.repository.UserRepository;
import com.bluetech.issuemanagement.service.IssueHistoryService;
import com.bluetech.issuemanagement.service.IssueService;
import com.bluetech.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueHistoryService issueHistoryService;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, UserRepository userRepository, ProjectRepository projectRepository, IssueHistoryService issueHistoryService, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.issueHistoryService = issueHistoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDto save(IssueDto issue) {

        issue.setDate(new Date());
        issue.setIssueStatus(IssueStatus.OPEN);

        Issue issueDb = modelMapper.map(issue, Issue.class);

        issueDb.setProject(projectRepository.getOne(issue.getProjectId()));
        issueDb = issueRepository.save(issueDb);

        issue.setId(issueDb.getId());
        return issue;
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map( issue, IssueDto.class);
    }

    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
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
    @Transactional
    public IssueDetailDto update(Long id, IssueUpdateDto issue) {
        Issue issueDb = issueRepository.getOne(id);
        User user = userRepository.getOne(issue.getAssignee_id());
        issueHistoryService.addHistory(id,issueDb);

        issueDb.setAssignee(user);
        issueDb.setDate(issue.getDate());
        issueDb.setDescription(issue.getDescription());
        issueDb.setDetails(issue.getDetails());
        issueDb.setIssueStatus(issue.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueRepository.save(issueDb);
        return getByIdWithDetails(id);
    }
}
