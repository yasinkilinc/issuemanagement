package com.bluetech.issuemanagement.service;

import com.bluetech.issuemanagement.dto.RegistrationRequest;
import com.bluetech.issuemanagement.dto.UserDto;
import com.bluetech.issuemanagement.entity.User;
import com.bluetech.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User save(User user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    Boolean delete(User user);

    User getByUsername(String username);

    List<UserDto> getAll();

    UserDto update(Long id, UserDto userDto);

    UserDto save(UserDto userDto);

    Boolean register(RegistrationRequest registrationRequest);

}
