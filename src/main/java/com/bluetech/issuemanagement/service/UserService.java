package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.UserDto;
import com.bluetech.issuemanagement.entity.User;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user);

    User getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    Boolean delete(User user);

    User getByUsername(String username);

}
