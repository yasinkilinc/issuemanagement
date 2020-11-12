package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.entity.IssueHistory;
import com.bluetech.issuemanagement.repository.IssueHistoryRepository;
import com.bluetech.issuemanagement.service.IssueHistoryService;
import com.bluetech.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        if(null == issueHistory.getDate()){
            throw new IllegalArgumentException("Issue history date can not be null");
        }

        return issueHistoryRepository.save(issueHistory);
    }

    @Override
    public IssueHistory getById(Long id) {
        return issueHistoryRepository.getOne(id);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<IssueHistory> data = issueHistoryRepository.findAll(pageable);
        return new TPage<IssueDto>(data, Arrays.asList( modelMapper.map( data.getContent() , IssueDto[].class) ));
    }

    @Override
    public Boolean delete(IssueHistory issueHistory) {
        issueHistoryRepository.delete(issueHistory);
        return Boolean.TRUE;
    }
}
