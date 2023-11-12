package com.zfinance.services.exchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.exchange.ExchangePayload;
import com.zfinance.dto.request.exchange.rates.ExchangeRatesViewBody;
import com.zfinance.dto.response.exchange.ExchangeCalculateResponse;
import com.zfinance.mapper.ClientCoinMapper;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.exchange.ExchangeSuccess;
import com.zfinance.orm.exchange.rates.ExchangeRate;
import com.zfinance.orm.userdefinedtypes.exchanges.ExchangeRateUdt;
import com.zfinance.orm.userdefinedtypes.exchanges.ExchangeSuccessChildren;
import com.zfinance.repositories.exchange.ExchangeSuccessRepository;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.exchange.rates.ExchangeRatesService;
import com.zfinance.services.external.IssuerService;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private ExchangeSuccessRepository exchangeSuccessRepository;

	@Autowired
	private ExchangeRatesService exchangeRatesService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public ExchangeCalculateResponse exchangeCalculate(ExchangePayload exchangePayload) {
		Wallet inCoin = null;
		Wallet outCoin = null;
		ExchangeRatesViewBody exchangeRatesViewBody = null;
		ExchangeCalculateResponse exchangeCalculateResponse = new ExchangeCalculateResponse();
		ExchangeRateUdt exchangeRateUdt = new ExchangeRateUdt();
		Double rate = 1.0;

		if (exchangePayload.getInCoinSerial() != null)
			inCoin = walletService.getWalletById(exchangePayload.getInCoinSerial());
		if (exchangePayload.getOutCoinSerial() != null)
			outCoin = walletService.getWalletById(exchangePayload.getOutCoinSerial());

		if (inCoin != null && inCoin.getIssuer() != null && inCoin.getIssuer().getId() != null && outCoin != null
				&& outCoin.getIssuer() != null && outCoin.getIssuer().getId() != null)
			exchangeRatesViewBody = new ExchangeRatesViewBody(inCoin.getIssuer().getId(), outCoin.getIssuer().getId());

		List<ExchangeRate> ratesByInAndOutIssuerIds = exchangeRatesService.getRatesByInAndOutIssuerIds(
				exchangeRatesViewBody);

		// TODO : what if we have multiple fares ?
		if (ratesByInAndOutIssuerIds != null && !ratesByInAndOutIssuerIds.isEmpty()) {
			ExchangeRate exchangeRate = ratesByInAndOutIssuerIds.get(0);
			rate = exchangeRate.getRate();
			if (exchangePayload.getAmount() != null) {
				// TODO : WICH AMOUNT WE TAKE FROM ? FUT OR AM OR AV
				inCoin.setAmount(inCoin.getAmount() - exchangePayload.getAmount());
				Double rateAmout = exchangePayload.getAmount() * rate;
				outCoin.setAmount(outCoin.getAmount() + rateAmout);
				walletService.saveWallet(inCoin);
				walletService.saveWallet(outCoin);

				exchangeCalculateResponse.setTopUpAmount(rateAmout);
				exchangeCalculateResponse.setWithdrawAmount(exchangePayload.getAmount());

			}
			exchangeRateUdt.setId(exchangeRate.getId());
			exchangeRateUdt.setInIssuer(inCoin.getIssuer());
			exchangeRateUdt.setOutIssuer(outCoin.getIssuer());
			exchangeRateUdt.setRate(rate);
			exchangeCalculateResponse.setExchangeRate(exchangeRateUdt);
		}

		return exchangeCalculateResponse;
	}

	@Override
	public ExchangeSuccess exchanges(ExchangePayload exchangePayload) {
		Wallet inCoin = null;
		Wallet outCoin = null;
		ExchangeCalculateResponse exchangeCalculate = exchangeCalculate(exchangePayload);
		ExchangeSuccess exchangeSuccess = new ExchangeSuccess();
		if (exchangeSuccess.getId() == null)
			exchangeSuccess.setId(sequenceGeneratorService.generateSequence(ExchangeSuccess.SEQUENCE_NAME));

		String currDateString = (new Date()).toString();
		exchangeSuccess.setCreatedAt(currDateString);
		exchangeSuccess.setType(exchangePayload.getExchangeType());

		if (exchangePayload.getInCoinSerial() != null)
			inCoin = walletService.getWalletById(exchangePayload.getInCoinSerial());
		if (exchangePayload.getOutCoinSerial() != null)
			outCoin = walletService.getWalletById(exchangePayload.getOutCoinSerial());

		List<ExchangeSuccessChildren> children = new ArrayList<>();
		ExchangeSuccessChildren from = new ExchangeSuccessChildren();
		from.setCoin(ClientCoinMapper.INSTANCE.mapWallet(inCoin));
		from.setCreatedAt(currDateString);
		from.setIncomingAmount(exchangeCalculate.getTopUpAmount());
		from.setOutgoingAmount(exchangeCalculate.getWithdrawAmount());
		from.setType(exchangePayload.getExchangeType());
		children.add(from);
		ExchangeSuccessChildren to = new ExchangeSuccessChildren();
		to.setCoin(ClientCoinMapper.INSTANCE.mapWallet(outCoin));
		to.setCreatedAt(currDateString);
		to.setIncomingAmount(exchangeCalculate.getTopUpAmount());
		to.setOutgoingAmount(exchangeCalculate.getWithdrawAmount());
		to.setType(exchangePayload.getExchangeType());
		children.add(to);
		exchangeSuccess.setChildren(children);

		// need to add transaction with type transfer

		return exchangeSuccessRepository.save(exchangeSuccess);
	}

}
