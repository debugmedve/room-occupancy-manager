package hu.pilopsoft.demo.room;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Guest implements Comparable<Guest> {

    private static final BigDecimal _100 = BigDecimal.valueOf(100);
    private final BigDecimal price;

    public boolean isPremium() {
        return _100.compareTo(price) <= 0;
    }

    @Override
    public int compareTo(Guest o) {
        return this.price.compareTo(o.price);
    }
}
