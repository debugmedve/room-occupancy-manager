package hu.pilopsoft.demo.service;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RoomServiceImpl.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class RoomServiceTest {

    private static final BigDecimal[] DEFAULT_GUESTS = new BigDecimal[]{
            BigDecimal.valueOf(23),
            BigDecimal.valueOf(45),
            BigDecimal.valueOf(155),
            BigDecimal.valueOf(374),
            BigDecimal.valueOf(22),
            BigDecimal.valueOf(99.99),
            BigDecimal.valueOf(100),
            BigDecimal.valueOf(101),
            BigDecimal.valueOf(115),
            BigDecimal.valueOf(209)
    };


    @Autowired
    private RoomService roomService;

    @ParameterizedTest
    @MethodSource("testRoomServiceCalculateMethodSource")
    void testRoomServiceCalculate(RoomRequest roomRequest, RoomResponse expectedRoomResponse) {
        final var result = roomService.calculate(roomRequest);
        assertResult(result, expectedRoomResponse);
    }

    public static Stream<Arguments> testRoomServiceCalculateMethodSource() {
        return Stream.of(
                Arguments.of(createRoomRequest(3, 3), createRoomResponse(3, BigDecimal.valueOf(738), 3, BigDecimal.valueOf(167.99))),
                Arguments.of(createRoomRequest(7, 5), createRoomResponse(6, BigDecimal.valueOf(1054), 4, BigDecimal.valueOf(189.99))),
                Arguments.of(createRoomRequest(2, 7), createRoomResponse(2, BigDecimal.valueOf(583), 4, BigDecimal.valueOf(189.99))),
                Arguments.of(createRoomRequest(7, 1), createRoomResponse(7, BigDecimal.valueOf(1153), 1, BigDecimal.valueOf(45.99)))
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

    private static RoomRequest createRoomRequest(final Integer economyCount, final Integer premiumCount, final BigDecimal[] guests) {
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
