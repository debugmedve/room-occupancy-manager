package hu.pilopsoft.demo.service;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Override
    public RoomResponse calculate(RoomRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
