package com.zfinance.controller.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.request.coin.UpdateWalletBody;
import com.zfinance.dto.request.coin.WalletBody;
import com.zfinance.dto.response.coin.GetWalletResponse;
import com.zfinance.dto.response.coin.WalletRecord;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.mapper.WalletMapper;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.external.AuthManagerService;

@RestController
@RequestMapping("/coins")
@CrossOrigin("*")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private AuthManagerService authManagerService;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@GetMapping
	public GetWalletResponse getWallets() {
		GetWalletResponse getWalletResponse = new GetWalletResponse();
		getWalletResponse.setCoins(WalletMapper.INSTANCE.mapWallets(walletService.getWallets()));
		return getWalletResponse;
	}

	@GetMapping("/signedIn")
	public GetWalletResponse getSignedInWallets() {
		String token = tokenAuthorizationFilter.getToken();
		UserRecord user = authManagerService.getUserFromToken(token);
		GetWalletResponse getWalletResponse = new GetWalletResponse();
		getWalletResponse.setCoins(WalletMapper.INSTANCE.mapWallets(walletService.getWalletsByUserId(user.getId())));
		return getWalletResponse;
	}

	@PostMapping
	public WalletRecord createWallet(@RequestBody WalletBody walletBody) {
		return WalletMapper.INSTANCE.mapWallet(walletService.createWallet(walletBody));
	}

	@PatchMapping("/{serial}")
	public WalletRecord updateWallet(@PathVariable String serial, @RequestBody UpdateWalletBody updateWalletBody) {
		return WalletMapper.INSTANCE.mapWallet(walletService.updateWallet(serial, updateWalletBody));
	}

	@PatchMapping("/{serial}/status")
	public WalletRecord updateWalletStatus(@PathVariable String serial) {
		return WalletMapper.INSTANCE.mapWallet(walletService.updateWalletStatus(serial));
	}

	@DeleteMapping("/{serial}")
	public WalletRecord deleteWallet(@PathVariable String serial) {
		return WalletMapper.INSTANCE.mapWallet(walletService.deleteWallet(serial));
	}

}
