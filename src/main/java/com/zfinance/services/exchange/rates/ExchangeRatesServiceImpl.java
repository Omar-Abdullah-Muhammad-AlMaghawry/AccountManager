package com.zfinance.services.exchange.rates;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.exchange.rates.ExchangeRatesCreateBody;
import com.zfinance.dto.request.exchange.rates.ExchangeRatesViewBody;
import com.zfinance.dto.request.exchange.rates.SetExchangeRatePayload;
import com.zfinance.dto.response.exchange.rates.ExchangeRatesCalculateResponse;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.mapper.ClientCoinMapper;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.exchange.rates.ExchangeRate;
import com.zfinance.orm.exchange.rates.ExchangeRatesSuccess;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.repositories.exchange.rates.ExchangeRateRepository;
import com.zfinance.repositories.exchange.rates.ExchangeRatesSuccessRepository;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.IssuerService;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Autowired
	private ExchangeRatesSuccessRepository exchangeRatesSuccessRepository;

	@Autowired
	private WalletService walletService;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public List<ExchangeRate> getRatesByInAndOutIssuerIds(ExchangeRatesViewBody exchangeRatesViewBody) {
		Criteria criteria = new Criteria();

		// Add filter criteria based on the filter object
		if (exchangeRatesViewBody != null) {
			if (exchangeRatesViewBody.getInIssuerId() != null) {
				criteria.and("inIssuer.id").is(exchangeRatesViewBody.getInIssuerId());
			}
			if (exchangeRatesViewBody.getOutIssuerId() != null) {
				criteria.and("outIssuer.id").is(exchangeRatesViewBody.getOutIssuerId());
			}
		}
		// Add other filter criteria as needed...

		Query query = new Query(criteria);

		// Apply sorting if needed
		// query.with(Sort.by(Sort.Order.asc("fieldName")));

		return mongoTemplate.find(query, ExchangeRate.class);
	}

	@Override
	public ExchangeRatesCalculateResponse exchangeCalculate(String rateId,
			ExchangeRatesCreateBody exchangeRatesCreateBody) {

		// TODO: NEED TO KNOW THE CALCULATION
		ExchangeRate exchangeRate = exchangeRateRepository.findById(rateId).orElseThrow(() -> new DataNotFoundException(
				ExchangeRate.class, rateId));

		Wallet inCoin = walletService.getWalletById(exchangeRatesCreateBody.getInCoin());
		Wallet outCoin = walletService.getWalletById(exchangeRatesCreateBody.getOutCoin());

		ExchangeRatesCalculateResponse exchangeRatesCalculateResponse = new ExchangeRatesCalculateResponse();

		return null;
	}

	@Override
	public ExchangeRatesSuccess exchanges(String rateId, ExchangeRatesCreateBody exchangeRatesCreateBody) {
		// TODO: NEED TO CHECK CALCULATION

		ExchangeRate exchangeRate = exchangeRateRepository.findById(rateId).orElseThrow(() -> new DataNotFoundException(
				ExchangeRate.class, rateId));

		Wallet inCoin = null;
		Wallet outCoin = null;

		ExchangeRatesSuccess exchangeRatesSuccess = new ExchangeRatesSuccess();
		exchangeRatesSuccess.setId(sequenceGeneratorService.generateSequence(ExchangeRatesSuccess.SEQUENCE_NAME));

		exchangeRatesSuccess.setCreatedAt((new Date()).toString());

		if (exchangeRatesCreateBody != null) {
			exchangeRatesSuccess.setIncomingAmount(exchangeRatesCreateBody.getInAmount());

			if (exchangeRatesCreateBody.getInAmount() != null && exchangeRate != null && exchangeRate.getRate() != null)
				exchangeRatesSuccess.setOutgoingAmount(exchangeRatesCreateBody.getInAmount() * exchangeRate.getRate());

			if (exchangeRatesCreateBody.getInCoin() != null)
				inCoin = walletService.getWalletById(exchangeRatesCreateBody.getInCoin());

			if (exchangeRatesCreateBody.getOutCoin() != null)
				outCoin = walletService.getWalletById(exchangeRatesCreateBody.getOutCoin());
		}

		if (outCoin != null)
			exchangeRatesSuccess.setSrcClientCoin(ClientCoinMapper.INSTANCE.mapWallet(outCoin));

		if (inCoin != null)
			exchangeRatesSuccess.setDestClientCoin(ClientCoinMapper.INSTANCE.mapWallet(inCoin));

		return exchangeRatesSuccessRepository.save(exchangeRatesSuccess);
	}

	@Override
	public ExchangeRate setExchangeRate(SetExchangeRatePayload setExchangeRatePayload) {
		ExchangeRate exchangeRate = new ExchangeRate();

		exchangeRate.setId(setExchangeRatePayload.getId());
		if (exchangeRate.getId() == null)
			exchangeRate.setId(sequenceGeneratorService.generateSequence(ExchangeRate.SEQUENCE_NAME));

		exchangeRate.setRate(setExchangeRatePayload.getRate());
		exchangeRate.setActive(setExchangeRatePayload.getActive());
		exchangeRate.setDirection(setExchangeRatePayload.getDirection());

		Issuer inIssuer = null;
		Issuer outIssuer = null;

		if (setExchangeRatePayload.getInIssuerId() != null)
			inIssuer = issuerService.getIssuerById(setExchangeRatePayload.getInIssuerId());
		if (setExchangeRatePayload.getOutIssuerId() != null)
			outIssuer = issuerService.getIssuerById(setExchangeRatePayload.getOutIssuerId());

		exchangeRate.setInIssuer(inIssuer);
		exchangeRate.setOutIssuer(outIssuer);

		return exchangeRateRepository.save(exchangeRate);

	}

	@Override
	public ExchangeRate getExchangeRateById(String id) {
		return exchangeRateRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ExchangeRate.class, id));
	}

}
