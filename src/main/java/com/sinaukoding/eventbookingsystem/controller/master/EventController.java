package com.sinaukoding.eventbookingsystem.controller.master;

import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.master.EventService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> createEventController(@RequestBody EventRequestRecord request) {
        eventService.createEvent(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("find-all")
    @Parameters({
            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
    })
    public BaseResponse<?> findAllEventController(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
            @RequestBody EventFilterRecord filterRequest
            ) {
        return BaseResponse.ok("success", eventService.findAllEvent(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?>findByIdEventController(@PathVariable String id) {
        return BaseResponse.ok("Berhasil mengambil data dengan id" + id, eventService.findById(id));
    }

    @PostMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> editEventController(@RequestBody EventRequestRecord request){
        eventService.editEvent(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> deleteEventController(@PathVariable String id){
        eventService.delete(id);
        return BaseResponse.ok("Data berhasil dihapus", null);
    }

}
