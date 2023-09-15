package com.zfinance.services.coin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.coin.UpdateWalletBody;
import com.zfinance.dto.request.coin.WalletBody;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.userdefinedtypes.exchangerates.Issuer;
import com.zfinance.repositories.coin.WalletRepository;
import com.zfinance.services.external.IssuerService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private IssuerService issuerService;

	@Override
	public List<Wallet> getWallets() {
		return walletRepository.findAll();
	}

	@Override
	public Wallet createWallet(WalletBody walletBody) {
		Wallet wallet = new Wallet();
		wallet.setSerial(UUID.randomUUID().toString());
		wallet.setActive(true);
		wallet.setAmount(Double.valueOf(0));
		wallet.setName(walletBody.getName());
		wallet.setType(walletBody.getType());

		Issuer issuer = new Issuer();
//		issuer = issuerService.getIssuerById(walletBody.getIssuerId());
		wallet.setIssuer(issuer);

		return walletRepository.save(wallet);
	}

	@Override
	public Wallet updateWallet(String serial, UpdateWalletBody updateWalletBody) {
		Optional<Wallet> walletOptional = walletRepository.findById(serial);

		if (walletOptional.isPresent()) {
			Wallet wallet = walletOptional.get();
			wallet.setName(updateWalletBody.getName());
			wallet.setActive(updateWalletBody.getActive());

			return walletRepository.save(wallet);
		} else {
			throw new DataNotFoundException(Wallet.class, serial);
		}
	}

	@Override
	public Wallet updateWalletStatus(String serial) {
		Optional<Wallet> walletOptional = walletRepository.findById(serial);

		if (walletOptional.isPresent()) {
			Wallet wallet = walletOptional.get();

			// TODO: SET STATUS .. WHAT STATUS ?
			return walletRepository.save(wallet);
		} else {
			throw new DataNotFoundException(Wallet.class, serial);
		}
	}

	@Override
	public Wallet deleteWallet(String serial) {
		Optional<Wallet> walletOptional = walletRepository.findById(serial);

		if (walletOptional.isPresent()) {
			Wallet wallet = walletOptional.get();
			walletRepository.delete(wallet);
			return wallet;
		} else {
			throw new DataNotFoundException(Wallet.class, serial);
		}
	}

}
