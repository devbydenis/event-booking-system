package com.sinaukoding.eventbookingsystem;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.mapper.managementuser.UserMapper;
import com.sinaukoding.eventbookingsystem.model.enums.Role;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.managementuser.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testAddUser_Success() {
        var request = new UserRequestRecord(null, "denis", "denisrahmadi", "denis@gmail.com",
                "denis123", Role.ADMIN);

        var userEntity = new User();
        when(userMapper.requestToEntity(request)).thenReturn(userEntity);

        // whrn
        userService.addUser(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(userRepository, times(1)).save(userEntity);


    }

}

