package com.zfinance.services.collect;

import com.zfinance.dto.request.collect.CollectBody;
import com.zfinance.orm.transaction.Transaction;

public interface CollectService {

	public Transaction create(CollectBody collectBody);
}
