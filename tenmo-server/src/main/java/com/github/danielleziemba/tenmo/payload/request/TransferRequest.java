package com.github.danielleziemba.tenmo.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {
    @NotBlank
    private Long userId;

    @NotBlank
    private BigDecimal amount;
}
