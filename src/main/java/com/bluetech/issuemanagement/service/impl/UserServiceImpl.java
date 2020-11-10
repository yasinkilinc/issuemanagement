package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.UserDto;
import com.bluetech.issuemanagement.entity.User;
import com.bluetech.issuemanagement.repository.UserRepository;
import com.bluetech.issuemanagement.service.UserService;
import com.bluetech.issuemanagement.util.TPage;
import java.util.Arrays;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(User user) {

        if(null == user.getEmail()){
            throw new IllegalArgumentException("User email can not be null");
        }

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        return new TPage<UserDto>(data, Arrays.asList( modelMapper.map( data.getContent(), UserDto[].class )));
    }

    @Override
    public Boolean delete(User user) {
        userRepository.delete(user);
        return Boolean.TRUE;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }
}
