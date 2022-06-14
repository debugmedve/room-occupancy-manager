package hu.pilopsoft.demo.service;

import hu.pilopsoft.demo.controller.dto.RoomRequest;
import hu.pilopsoft.demo.controller.dto.RoomResponse;
import hu.pilopsoft.demo.room.Guest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    private static final String PREMIUM = "premium";
    private static final String ECONOMY = "economy";

    private interface DequeList<T> extends Deque<T>, List<T> {}
    private static class DequeListImpl<T> extends LinkedList<T> implements DequeList<T> {}

    private static final Function<? super Guest, String> KEY_FUNCTION = guest -> guest.isPremium() ? PREMIUM : ECONOMY;

    private static final Function<? super Guest, DequeList<Guest>> VALUE_FUNCTION = guest -> {
        final DequeList<Guest> result = new DequeListImpl<>();
        result.add(guest);
        return result;
    };

    private static final BinaryOperator<DequeList<Guest>> MERGE_FUNCTION = (existing, replacement) ->  {
        existing.addAll(replacement);
        Collections.sort(existing);
        return existing;
    };

    @Override
    public RoomResponse calculate(final RoomRequest request) {
        final var sortedGuestMap = request.getGuests().stream()
                .map(Guest::new)
                .collect(Collectors.toMap(KEY_FUNCTION, VALUE_FUNCTION, MERGE_FUNCTION));
        log.trace("Sorted guest map: {}", sortedGuestMap);

        final DequeList<Guest> premiumGuests = Optional.ofNullable(sortedGuestMap.get(PREMIUM)).orElse(new DequeListImpl<>());
        final DequeList<Guest> economyGuests = Optional.ofNullable(sortedGuestMap.get(ECONOMY)).orElse(new DequeListImpl<>());

        final var result = new RoomResponse();
        populatePremiumResult(premiumGuests, request.getPremiumRoomCount(), result);
        populateEconomyResult(economyGuests, request.getEconomyRoomCount(), request.getPremiumRoomCount(), result);

        return result;
    }

    private void populateEconomyResult(final Deque<Guest> economyGuests, final int economyRoomCount, final int premiumRoomCount, final RoomResponse result) {
        var total = BigDecimal.ZERO;
        result.setEconomyUsage(0);
        final var guestIterator = economyGuests.descendingIterator();

        if (premiumRoomCount != result.getPremiumUsage()) {
            populateRemainingPremiumWithEconomyGuests(premiumRoomCount, result, guestIterator);
        }

        for(int i = 0; i < economyRoomCount && guestIterator.hasNext(); ++i) {
            final var guest = guestIterator.next();
            total = total.add(guest.getPrice());
            result.setEconomyUsage(result.getEconomyUsage() + 1);
        }

        result.setEconomyTotal(total);

    }

    private void populateRemainingPremiumWithEconomyGuests(int premiumRoomCount, RoomResponse result, Iterator<Guest> guestIterator) {
        final var remainingCount = premiumRoomCount - result.getPremiumUsage();
        final DequeList<Guest> remainingGuests = new DequeListImpl<>();

        for (int i = 0; i < remainingCount && guestIterator.hasNext(); ++i) {
            remainingGuests.add(guestIterator.next());
        }

        populatePremiumResult(remainingGuests, remainingCount, result);
    }

    private void populatePremiumResult(final Deque<Guest> premiumGuests, final int premiumRoomCount, final RoomResponse result) {
        var total = Optional.ofNullable(result.getPremiumTotal()).orElse(BigDecimal.ZERO);

        if (result.getPremiumUsage() == null) {
            result.setPremiumUsage(0);
        }

        final var guestIterator = premiumGuests.descendingIterator();

        for(int i = 0; i < premiumRoomCount && guestIterator.hasNext(); ++i) {
            final var guest = guestIterator.next();
            total = total.add(guest.getPrice());
            result.setPremiumUsage(result.getPremiumUsage()+1);
        }

        result.setPremiumTotal(total);
    }


}
