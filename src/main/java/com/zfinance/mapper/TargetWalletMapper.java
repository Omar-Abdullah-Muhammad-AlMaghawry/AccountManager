package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.userdefinedtypes.transaction.Target;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TargetWalletMapper {

	TargetWalletMapper INSTANCE = Mappers.getMapper(TargetWalletMapper.class);

	public Target mapWallet(Wallet wallet);

	public Wallet mapTarget(Target target);

	public default Page<Target> mapWallets(Page<Wallet> wallets) {
		return wallets.map(this::mapWallet);
	}

	public List<Target> mapWallets(List<Wallet> wallets);

}