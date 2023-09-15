package com.zfinance.services.external;

import com.zfinance.orm.userdefinedtypes.exchangerates.Issuer;

public interface IssuerService {

	public Issuer getIssuerById(String id);

}
