package com.zfinance.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.response.coin.CoinsResponse;
import com.zfinance.mapper.CoinMapper;
import com.zfinance.services.coin.WalletService;

@RestController
@RequestMapping("/organizations")
@CrossOrigin("*")
public class OrganizationController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/user/{id}/coins")
	public CoinsResponse getWalletsByUserId(@PathVariable String id) {
		CoinsResponse coinsResponse = new CoinsResponse();
		coinsResponse.setCoins(CoinMapper.INSTANCE.mapWallets(walletService.getWalletsByUserId(id)));
		return coinsResponse;
	}

	@GetMapping("/{id}/coins")
	public CoinsResponse getWalletsByOrganizationId(@PathVariable String id) {
		CoinsResponse coinsResponse = new CoinsResponse();
		coinsResponse.setCoins(CoinMapper.INSTANCE.mapWallets(walletService.getWalletsByOrganizationId(id)));
		return coinsResponse;
	}

	@GetMapping("/{id}/provider-coins")
	public CoinsResponse getProviderCoins(@PathVariable String id) {
		CoinsResponse coinsResponse = new CoinsResponse();
		coinsResponse.setCoins(CoinMapper.INSTANCE.mapWallets(walletService.getWalletsByOrganizationId(id)));
		return coinsResponse;
	}

}
