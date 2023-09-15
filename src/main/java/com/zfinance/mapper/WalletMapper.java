package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.coin.WalletRecord;
import com.zfinance.orm.coin.Wallet;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMapper {

	WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

	public WalletRecord mapWallet(Wallet wallet);

	public Wallet mapWalletRecord(WalletRecord walletRecord);

	public default Page<WalletRecord> mapWallets(Page<Wallet> wallets) {
		return wallets.map(this::mapWallet);
	}

	public List<WalletRecord> mapWallets(List<Wallet> wallets);

}
