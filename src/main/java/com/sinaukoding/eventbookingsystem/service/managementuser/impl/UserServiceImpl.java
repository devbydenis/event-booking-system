package com.sinaukoding.eventbookingsystem.service.managementuser.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.mapper.managementuser.UserMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.service.managementuser.UserService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserRequestRecord request){
        validasiMandatory(request);

        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException(request.email() + " sudah digunakan");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException(request.username() + " sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public void editUser(UserRequestRecord request){
        validasiMandatory(request);

        var userExisting = userRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data user tidak ditemukan"));

        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException(request.email() + " sudah digunakan");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException(request.username() + " sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);
        user.setId(userExisting.getId());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public Page<SimpleMap> findAllUser(UserFilterRecord filterRequest, Pageable pageable){
        CustomBuilder<User> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotBlankLike("username", filterRequest.username(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<User> listAllUser = userRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listAllUser.stream().map(user -> {
            SimpleMap data = SimpleMap.createMap()
                    .add("id", user.getId())
                    .add("nama", user.getNama())
                    .add("username", user.getUsername())
                    .add("email", user.getEmail())
                    .add("role", user.getRole().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listAllUser.getTotalElements());
    }

    @Override
    public void deleteUser(String id){
        var userExisting = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data user tidak ditemukan"));
        userRepository.deleteById(id);
    }

//  ---------------------------------------------------------------------------
    private void validasiMandatory(UserRequestRecord request) {
        if (request.nama() == null || request.nama().isEmpty()) {
            throw new RuntimeException("Nama tidak boleh kosong");
        }
        if (request.username() == null || request.username().isEmpty()) {
            throw new RuntimeException("Username tidak boleh kosong");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Password tidak boleh kosong");
        }
        if (request.role() == null) {
            throw new RuntimeException("Role tidak boleh kosong");
        }
    }
}
