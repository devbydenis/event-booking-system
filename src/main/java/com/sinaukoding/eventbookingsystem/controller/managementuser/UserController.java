package com.sinaukoding.eventbookingsystem.controller.managementuser;

import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
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

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> addUserController(@RequestBody UserRequestRecord request){
        userService.addUser(request);
        return BaseResponse.ok("Data user berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> editUserController(@RequestBody UserRequestRecord request){
        userService.editUser(request);
        return BaseResponse.ok("Data user berhasil diubah", null);
    }

    @PostMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> deleteUserController(String id){
        userService.deleteUser(id);
        return BaseResponse.ok("Data user berhasil dihapus", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAllUserController(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
            @RequestBody UserFilterRecord filterRequest
    ){
        return BaseResponse.ok(null, userService.findAllUser(filterRequest, pageable));
    }

//    @GetMapping("find-by-id/{id}")
//    public BaseResponse<?> findByIdBookingController(@PathVariable String id){
//        return BaseResponse.ok("Berhasil memuat booking berdasarkan id", userService.findByIdBooking(id));
//    }
}
