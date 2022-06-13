package hu.pilopsoft.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RoomResponse implements Serializable {

    private Integer premiumUsage;
    private Integer economyUsage;

    private BigDecimal premiumTotal;
    private BigDecimal economyTotal;

}
