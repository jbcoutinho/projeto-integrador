package com.concat.projetointegrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ShippingFeeDTO {

		@JsonProperty("taxaDeEnvio")
		private BigDecimal shippingFee;

}
