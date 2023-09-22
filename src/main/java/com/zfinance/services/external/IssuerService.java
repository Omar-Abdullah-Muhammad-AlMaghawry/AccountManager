package com.zfinance.services.external;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;

public interface IssuerService {

	public CoinIssuer getIssuerById(String id);

}
