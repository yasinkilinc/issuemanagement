package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    IssueDto update(Long id, IssueDto issueDto);
}
