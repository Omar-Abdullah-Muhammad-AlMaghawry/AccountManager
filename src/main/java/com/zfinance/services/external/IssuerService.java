package com.zfinance.services.external;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

public interface IssuerService {

	public Issuer getIssuerById(String id);

	public Issuer getIssuerByCurrencyCode(String code);

}
