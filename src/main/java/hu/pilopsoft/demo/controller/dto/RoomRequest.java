package hu.pilopsoft.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RoomRequest implements Serializable {

    @NotNull
    private Integer premiumRoomCount;

    @NotNull
    private Integer economyRoomCount;

    @NotNull
    @Size(min = 1)
    private BigDecimal[] guests;
}
