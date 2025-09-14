package com.sinaukoding.eventbookingsystem;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.mapper.app.RegisterUserMapper;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.app.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private RegisterUserMapper registerUserMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testRegister_Success() {
        var request = new RegisterRequestRecord("denis", "denis1@gmail.com",
                "denisrahmadi", "denis123");

        var userEntity = new User();
        when(registerUserMapper.requestToEntity(request)).thenReturn(userEntity);

        // when
        authService.register(request);

        // then
        verify(validatorService, times(1)).validator(request);
    }


}
