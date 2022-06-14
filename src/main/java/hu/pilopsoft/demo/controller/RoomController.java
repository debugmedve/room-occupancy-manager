package hu.pilopsoft.demo.controller;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;
import hu.pilopsoft.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room/v0")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/calculate", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public RoomResponse postCalculateRooms(@RequestBody @Validated RoomRequest request) {
        return roomService.calculate(request);
    }

}
