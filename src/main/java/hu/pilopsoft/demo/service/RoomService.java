package hu.pilopsoft.demo.service;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;

/**
 * services for {@code /room} path
 */
public interface RoomService {

    /**
     * Calculates the total prices from the given number of free rooms and the potential customer's bids
     *
     * @param request {@link RoomRequest}
     * @return {@link RoomResponse}
     */
    RoomResponse calculate(RoomRequest request);

}
