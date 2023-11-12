package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.userdefinedtypes.transaction.ClientCoin;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientCoinMapper {

	ClientCoinMapper INSTANCE = Mappers.getMapper(ClientCoinMapper.class);

	public ClientCoin mapWallet(Wallet wallet);

	public Wallet mapClientCoin(ClientCoin clientCoin);

	public default Page<ClientCoin> mapWallets(Page<Wallet> wallets) {
		return wallets.map(this::mapWallet);
	}

	public List<ClientCoin> mapWallets(List<Wallet> wallets);

}
