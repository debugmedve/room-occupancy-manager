package hu.pilopsoft.demo.service;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RoomServiceImpl.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
class RoomServiceTest {

    private static final List<BigDecimal> DEFAULT_GUESTS = List.of(
            BigDecimal.valueOf(23),
            BigDecimal.valueOf(45),
            BigDecimal.valueOf(155), //p
            BigDecimal.valueOf(374), //p
            BigDecimal.valueOf(22),
            BigDecimal.valueOf(99.99),
            BigDecimal.valueOf(100), //p
            BigDecimal.valueOf(101), //p
            BigDecimal.valueOf(115), //p
            BigDecimal.valueOf(209) //p
    );


    @Autowired
    private RoomService roomService;

    @ParameterizedTest
    @MethodSource("testRoomServiceCalculateMethodSource")
    void testRoomServiceCalculate(RoomRequest roomRequest, RoomResponse expectedRoomResponse) {
        final var result = roomService.calculate(roomRequest);
        assertResult(result, expectedRoomResponse);
    }

    /**
     * TODO:: in the {@code CodingChallengeBE_v2.pdf}  there is 1 mistake.
     * <p>
     * Test4: the 45.99 is in the wrong total profit, because it is the promoted guest with 99.99
     *
     * @return arugments
     */
    public static Stream<Arguments> testRoomServiceCalculateMethodSource() {
        return Stream.of(
                Arguments.of(createRoomRequest(3, 3), createRoomResponse(3, BigDecimal.valueOf(738), 3, BigDecimal.valueOf(167.99))),
                Arguments.of(createRoomRequest(5, 7), createRoomResponse(6, BigDecimal.valueOf(1054), 4, BigDecimal.valueOf(189.99))),
                Arguments.of(createRoomRequest(7, 2), createRoomResponse(2, BigDecimal.valueOf(583), 4, BigDecimal.valueOf(189.99))),
                Arguments.of(createRoomRequest(1, 7), createRoomResponse(7, BigDecimal.valueOf(1153.99), 1, BigDecimal.valueOf(45))), // TEST4
                Arguments.of(createRoomRequest(2, 2, List.of(BigDecimal.valueOf(60), BigDecimal.valueOf(40), BigDecimal.valueOf(10), BigDecimal.valueOf(9.99))), createRoomResponse(2, BigDecimal.valueOf(100), 2, BigDecimal.valueOf(19.99))),
                Arguments.of(createRoomRequest(2, 5, List.of(BigDecimal.valueOf(60), BigDecimal.valueOf(40), BigDecimal.valueOf(10), BigDecimal.valueOf(9.99))), createRoomResponse(2, BigDecimal.valueOf(100), 2, BigDecimal.valueOf(19.99))),
                Arguments.of(createRoomRequest(5, 2, List.of(BigDecimal.valueOf(60), BigDecimal.valueOf(40), BigDecimal.valueOf(10), BigDecimal.valueOf(9.99))), createRoomResponse(0, BigDecimal.ZERO, 4, BigDecimal.valueOf(119.99))),
                Arguments.of(createRoomRequest(2, 2, List.of(BigDecimal.valueOf(100), BigDecimal.valueOf(101), BigDecimal.valueOf(102))), createRoomResponse(2, BigDecimal.valueOf(203), 0, BigDecimal.ZERO))
        );
    }

    private void assertResult(RoomResponse result, RoomResponse expectedResponse) {
        assertEquals(expectedResponse.getPremiumUsage(), result.getPremiumUsage());
        assertEquals(expectedResponse.getPremiumTotal(), result.getPremiumTotal());
        assertEquals(expectedResponse.getEconomyUsage(), result.getEconomyUsage());
        assertEquals(expectedResponse.getEconomyTotal(), result.getEconomyTotal());
    }

    private static RoomResponse createRoomResponse(int premiumUsage, BigDecimal premiumTotal, int economyUsage, BigDecimal economyTotal) {
        final var result = new RoomResponse();
        result.setPremiumUsage(premiumUsage);
        result.setPremiumTotal(premiumTotal);
        result.setEconomyUsage(economyUsage);
        result.setEconomyTotal(economyTotal);
        return result;
    }

    private static RoomRequest createRoomRequest(final Integer economyCount, final Integer premiumCount) {
        return createRoomRequest(economyCount, premiumCount, DEFAULT_GUESTS);
    }

    private static RoomRequest createRoomRequest(final Integer economyCount, final Integer premiumCount, final List<BigDecimal> guests) {
        final var result = new RoomRequest();
        result.setEconomyRoomCount(economyCount);
        result.setPremiumRoomCount(premiumCount);
        result.setGuests(guests);
        return result;
    }

    @Configuration
    public static class ContextConfiguration {

    }
}
