package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.IssueHistoryDto;
import com.bluetech.issuemanagement.entity.Issue;
import com.bluetech.issuemanagement.entity.IssueHistory;
import com.bluetech.issuemanagement.repository.IssueHistoryRepository;
import com.bluetech.issuemanagement.service.IssueHistoryService;
import com.bluetech.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {

    private final IssueHistoryRepository issueHistoryRepository;
    private final ModelMapper modelMapper;

    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository,
                                   ModelMapper modelMapper) {
        this.issueHistoryRepository = issueHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueHistory save(IssueHistory issueHistory) {
        if (null == issueHistory.getDate()) {
            throw new IllegalArgumentException("Issue history date can not be null");
        }

        return issueHistoryRepository.save(issueHistory);
    }

    @Override
    public IssueHistory getById(Long id) {
        return issueHistoryRepository.getOne(id);
    }


    @Override
    public List<IssueHistoryDto> getByIssueId(Long id) {
        return Arrays.asList(modelMapper.map(issueHistoryRepository.getByIssueIdOrderById(id), IssueHistoryDto[].class));
    }

    @Override
    public TPage<IssueHistoryDto> getAllPageable(Pageable pageable) {
        Page<IssueHistory> data = issueHistoryRepository.findAll(pageable);
        return new TPage<IssueHistoryDto>(data, Arrays.asList(modelMapper.map(data.getContent(), IssueHistoryDto[].class)));
    }

    @Override
    public Boolean delete(IssueHistory issueHistory) {
        issueHistoryRepository.delete(issueHistory);
        return Boolean.TRUE;
    }

    @Override
    public void addHistory(Long id, Issue issueDb) {
        IssueHistory history = new IssueHistory();
        history.setIssue(issueDb);
        history.setAssignee(issueDb.getAssignee());
        history.setDate(issueDb.getDate());
        history.setDescription(issueDb.getDescription());
        history.setDetails(issueDb.getDetails());
        history.setIssueStatus(issueDb.getIssueStatus());
        issueHistoryRepository.save(history);
    }
}
