package com.zfinance.orm.transfer;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.transfer.TransfersTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_transfer")
public class Transfer {

	@Id
	@Field("id")
	private String id;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("description")
	private String description;

	@Field("request_identifier")
	private Integer requestIdentifier;

	@Field("request_status")
	private String requestStatus;

	@Field("status")
	private String status;

	@Field("type")
	private String type;

	@Field("transactions")
	private List<TransfersTransaction> transactions;

	@Field("children")
	private List<Object> children;

}
