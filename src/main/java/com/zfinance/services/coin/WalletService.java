package com.zfinance.services.coin;

import java.util.List;

import com.zfinance.dto.request.coin.UpdateWalletBody;
import com.zfinance.dto.request.coin.WalletBody;
import com.zfinance.dto.response.coin.WalletRecord;
import com.zfinance.orm.coin.Wallet;

public interface WalletService {

	public List<Wallet> getWallets();

	public Wallet createWallet(WalletBody walletBody);

	public Wallet updateWallet(String serial, UpdateWalletBody updateWalletBody);

	public Wallet updateWalletStatus(String serial);

	public Wallet deleteWallet(String serial);

	public List<Wallet> getWalletsByUserId(String userId);

	public List<Wallet> getWalletsByOrganizationId(String organizationId);

	Wallet getWalletById(String id);

}
