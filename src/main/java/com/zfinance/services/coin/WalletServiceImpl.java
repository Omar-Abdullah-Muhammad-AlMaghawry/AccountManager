package com.zfinance.services.coin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.request.coin.UpdateWalletBody;
import com.zfinance.dto.request.coin.WalletBody;
import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.exceptions.BusinessException;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.repositories.coin.WalletRepository;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.AuthManagerService;
import com.zfinance.services.external.IssuerService;
import com.zfinance.services.external.UserService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private AuthManagerService authManagerService;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Wallet> searchWalletsByUserAndCurrency(String userId, String currency) {
		Criteria criteria = new Criteria();
		criteria.and("userId").is(userId);
		criteria.and("issuer.currency").is(currency);

		Query query = new Query(criteria);
		return mongoTemplate.find(query, Wallet.class);
	}

	@Override
	public List<Wallet> getWallets() {
		return walletRepository.findAll();
	}

	@Override
	public Wallet createWallet(WalletBody walletBody) {
		UserRecord user = null;
		if (walletBody.getUserId() == null) {
			String token = tokenAuthorizationFilter.getToken();
			user = authManagerService.getUserFromToken(token);
		} else {
			UsersFilter usersFilter = new UsersFilter();
			usersFilter.setIds(Collections.singletonList(walletBody.getUserId()));
			user = userService.searchUsers(usersFilter).get(0);
		}

		Issuer issuerBody = issuerService.getIssuerById(walletBody.getIssuerId());

		this.validateWallet(user, issuerBody);

		Wallet wallet = new Wallet();
		wallet.setSerial(UUID.randomUUID().toString());
		wallet.setActive(true);
		wallet.setAmount(Double.valueOf(0));
		wallet.setName(walletBody.getName());
		wallet.setType(walletBody.getType());

		wallet.setIssuer(issuerBody);

		if (user.getMembers() != null && !user.getMembers().isEmpty() && user.getMembers().get(0)
				.getOrganization() != null)
			wallet.setOrganizationId(user.getMembers().get(0).getOrganization().getId());

		wallet.setUserId(user.getId());

		return walletRepository.save(wallet);
	}

	private void validateWallet(UserRecord user, Issuer issuerBody) {
		List<Wallet> wallets = walletRepository.findAllByUserId(user.getId());
		for (Wallet userWallet : wallets) {
			if (userWallet.getIssuer().getId().equals(issuerBody.getId()))
				throw new BusinessException("error_alreadyHaveTheSameIssuer");
		}
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

	@Override
	public List<Wallet> getWalletsByUserId(String userId) {
		return walletRepository.findAllByUserId(userId);

	}

	@Override
	public List<Wallet> getWalletsByOrganizationId(String organizationId) {
		return walletRepository.findAllByOrganizationId(organizationId);

	}

	@Override
	public Wallet getWalletById(String id) {
		return walletRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Wallet.class, id));
	}

	@Override
	public Wallet saveWallet(Wallet wallet) {
		if (wallet.getSerial() == null)
			wallet.setSerial(sequenceGeneratorService.generateSequence(Wallet.SEQUENCE_NAME));
		return walletRepository.save(wallet);
	}

	@Override
	public List<Wallet> getSignedInWalletsByCurrency(String currency) {
		String token = tokenAuthorizationFilter.getToken();
		UserRecord user = authManagerService.getUserFromToken(token);

		// Create a query to search for wallets based on userId and currency
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(user.getId()).and("issuer.currency").is(currency));

		return mongoTemplate.find(query, Wallet.class);

	}

	@Override
	public List<Wallet> getWalletBySerial(String serial) {
		return walletRepository.findAllBySerial(serial);
	}

}
