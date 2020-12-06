package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.IssueHistoryDto;
import com.bluetech.issuemanagement.entity.Issue;
import com.bluetech.issuemanagement.entity.IssueHistory;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IssueHistoryService {

    IssueHistory save(IssueHistory issue);

    IssueHistory getById(Long id);

    List<IssueHistoryDto> getByIssueId(Long id);

    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueHistory issueHistory);

    void addHistory(Long id, Issue issueDb);
}
