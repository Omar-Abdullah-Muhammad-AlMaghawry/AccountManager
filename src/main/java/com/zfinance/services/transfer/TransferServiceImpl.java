package com.zfinance.services.transfer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.coin.WalletBody;
import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.request.transfer.TransferBody;
import com.zfinance.dto.response.transfer.PaymentToolDetails;
import com.zfinance.dto.response.transfer.TransferCalculateResponse;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.enums.PaymentToolTypeEnum;
import com.zfinance.mapper.TargetWalletMapper;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.userdefinedtypes.transfer.TransfersTransaction;
import com.zfinance.repositories.transfer.TransferRepository;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.IssuerService;
import com.zfinance.services.external.UserService;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private WalletService walletService;

	@Autowired
	private UserService userService;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public TransfersTransaction executeTransfer(TransferBody transferBody) {
		TransfersTransaction transfersTransaction = new TransfersTransaction();
		transfersTransaction.setAmount(transferBody.getAmount());
		transfersTransaction.setPerformedAt((new Date()).toString());

		if (transferBody.getPaymentTool() != null) {
			transfersTransaction.setType(transferBody.getPaymentTool().getType());
			if (transferBody.getPaymentTool().getType() == PaymentToolTypeEnum.COIN.getCode()) {
				Wallet fromWallet = walletService.getWalletById(transferBody.getPaymentTool().getSrcValue());
				transfersTransaction.setFrom(TargetWalletMapper.INSTANCE.mapWallet(fromWallet));
				transfersTransaction.setIssuer(fromWallet.getIssuer());

				if (transferBody.getPaymentTool().getDestValue().contains("@")) {
					UsersFilter usersFilter = new UsersFilter();
					usersFilter.setEmail(transferBody.getPaymentTool().getDestValue());
					List<UserRecord> toUsers = userService.searchUsers(usersFilter);
					if (!toUsers.isEmpty()) {
						UserRecord toUser = toUsers.get(0);
						if (toUser.getContact() != null && toUser.getContact().getEmailVerified() != null && toUser
								.getContact().getEmailVerified()) {
							List<Wallet> toWallets = walletService.getWalletsByUserId(toUser.getId());
							Wallet toWallet = null;
							if (!toWallets.isEmpty()) {
								toWallet = toWallets.stream().filter(wallet -> wallet.getIssuer().equals(fromWallet
										.getIssuer())).findFirst().orElse(null);
							}
							if (toWallet == null) {
								WalletBody walletBody = new WalletBody();
								walletBody.setIssuerId(fromWallet.getIssuer().getId());
								walletBody.setName(fromWallet.getIssuer().getCurrency() + " Account");
								walletBody.setType(null);
								toWallet = walletService.createWallet(walletBody);
							}
							transfersTransaction.setTo(TargetWalletMapper.INSTANCE.mapWallet(toWallet));
						}
					}
				}

			}
		}
		return transfersTransaction;
	}

	@Override
	public TransferCalculateResponse calculateCommissionFee(TransferBody transferBody) {
		TransfersTransaction transfersTransaction = executeTransfer(transferBody);
		TransferCalculateResponse transferCalculateResponse = new TransferCalculateResponse();
		transferCalculateResponse.setTransactionAmount(transferBody.getAmount());
		transferCalculateResponse.setStatus("done");
		transferCalculateResponse.setSenderAmountPush(transferBody.getAmount());

		PaymentToolDetails paymentToolDetails = new PaymentToolDetails();
		paymentToolDetails.setType(transfersTransaction.getType());

		if (transfersTransaction.getIssuer() != null) {
			transferCalculateResponse.setIssuer(transfersTransaction.getIssuer());
			paymentToolDetails.setCurrency(transfersTransaction.getIssuer().getCurrency());
			paymentToolDetails.setSymbol(transfersTransaction.getIssuer().getSymbol());
		}

		if (transfersTransaction.getTo() != null)
			paymentToolDetails.setDestId(transfersTransaction.getTo().getSerial());

		if (transfersTransaction.getFrom() != null)
			paymentToolDetails.setSrcId(transfersTransaction.getFrom().getSerial());

		transferCalculateResponse.setPaymentToolDetails(paymentToolDetails);

		return transferCalculateResponse;
	}

}
