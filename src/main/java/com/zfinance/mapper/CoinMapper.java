package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.coin.CoinRecord;
import com.zfinance.orm.coin.Wallet;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoinMapper {

	CoinMapper INSTANCE = Mappers.getMapper(CoinMapper.class);

	public CoinRecord mapWallet(Wallet wallet);

	public Wallet mapCoinRecord(CoinRecord coinRecord);

	public default Page<CoinRecord> mapWallets(Page<Wallet> wallets) {
		return wallets.map(this::mapWallet);
	}

	public List<CoinRecord> mapWallets(List<Wallet> wallets);

}
