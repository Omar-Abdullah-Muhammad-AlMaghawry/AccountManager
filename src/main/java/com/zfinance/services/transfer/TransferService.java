package com.zfinance.services.transfer;

import com.zfinance.dto.request.transfer.TransferBody;
import com.zfinance.dto.response.transfer.TransferCalculateResponse;
import com.zfinance.orm.userdefinedtypes.transfer.TransfersTransaction;

public interface TransferService {

	public TransfersTransaction executeTransfer(TransferBody transferBody);

	public TransferCalculateResponse calculateCommissionFee(TransferBody transferBody);

}
