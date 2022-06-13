package hu.pilopsoft.demo.room;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Room implements Comparable<Room> {

    private BigDecimal price;

    @Override
    public int compareTo(Room o) {
        return this.price.compareTo(o.price);
    }
}
