package com.bluetech.issuemanagement.service.impl;
/*
 * Created by yasinkilinc on 4.11.2020
 */

import com.bluetech.issuemanagement.dto.RegistrationRequest;
import com.bluetech.issuemanagement.dto.UserDto;
import com.bluetech.issuemanagement.entity.User;
import com.bluetech.issuemanagement.repository.UserRepository;
import com.bluetech.issuemanagement.service.UserService;
import com.bluetech.issuemanagement.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) {
        if (null == user.getEmail()) {
            throw new IllegalArgumentException("User email can not be null");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.getOne(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        return new TPage<UserDto>(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    @Override
    public Boolean delete(User user) {
        userRepository.delete(user);
        return Boolean.TRUE;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User userDb = userRepository.getOne(userDto.getId());

        if (userDb != null) {
            throw new IllegalArgumentException("Project code already exists");
        }

        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User userDb = userRepository.getOne(id);

        if (userDb == null) {
            throw new IllegalArgumentException("User does not exist id: " + id);
        }

        userDb.setNameSurname(userDto.getNameSurname());

        return modelMapper.map(userRepository.save(userDb), UserDto.class);
    }

    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }
}
