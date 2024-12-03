package com.zfinance.controller.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.transfer.TransferBody;
import com.zfinance.dto.response.transfer.TransferCalculateResponse;
import com.zfinance.orm.userdefinedtypes.transfer.TransfersTransaction;
import com.zfinance.services.transfer.TransferService;

@RestController
@RequestMapping("/transfers")
@CrossOrigin("*")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@PostMapping
	private TransfersTransaction executeTransfer(@RequestBody TransferBody transferBody) {
		return transferService.executeTransfer(transferBody);
	}

	@PostMapping("/calculate")
	private TransferCalculateResponse calculateCommissionFee(@RequestBody TransferBody transferBody) {
		return transferService.calculateCommissionFee(transferBody);
	}

}
