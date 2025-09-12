package com.sinaukoding.eventbookingsystem.controller.managementuser;

import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.managementuser.UserService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@NoArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAllUserController(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
            @RequestBody UserFilterRecord filterRequest
    ){
        return BaseResponse.ok(null, userService.findAllUser(filterRequest, pageable));
    }
}
