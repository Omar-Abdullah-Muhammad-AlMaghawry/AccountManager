package com.zfinance.dto.response.coin;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoinsResponse {
	private List<CoinRecord> coins;
}
