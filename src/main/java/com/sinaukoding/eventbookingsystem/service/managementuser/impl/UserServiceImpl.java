package com.sinaukoding.eventbookingsystem.service.managementuser.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.service.managementuser.UserService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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


}
