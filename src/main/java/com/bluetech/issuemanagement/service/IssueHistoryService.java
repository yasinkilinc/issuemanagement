package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.entity.IssueHistory;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IssueHistoryService {

    IssueHistory save(IssueHistory issue);

    IssueHistory getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueHistory issueHistory);

}
