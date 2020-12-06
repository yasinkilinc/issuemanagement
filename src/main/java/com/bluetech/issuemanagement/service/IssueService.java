package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.IssueDetailDto;
import com.bluetech.issuemanagement.dto.IssueDto;
import com.bluetech.issuemanagement.dto.IssueUpdateDto;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    IssueDetailDto update(Long id, IssueUpdateDto issueDto);
}
