package hu.pilopsoft.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class RoomRequest implements Serializable {

    @NotNull
    @Min(0)
    private Integer premiumRoomCount;

    @NotNull
    @Min(0)
    private Integer economyRoomCount;

    @NotNull
    @Size(min = 1)
    private List<BigDecimal> guests;
}
