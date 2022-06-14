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
     * Our hotel clients have two different categories of rooms: Premium and Economy. Our
     * hotels want their customers to be satisfied: they will not book a customer willing to
     * pay EUR 100 or more for the night into an Economy room. But they will book lower
     * paying customers into Premium rooms if these rooms are empty and all Economy
     * rooms are occupied with low paying customers. The highest paying customers below
     * EUR 100 will get preference for the “upgrade”. Customers always only have one
     * specific price they are willing to pay for the night.
     *
     * @param request {@link RoomRequest}
     * @return {@link RoomResponse}
     */
    RoomResponse calculate(RoomRequest request);

}
