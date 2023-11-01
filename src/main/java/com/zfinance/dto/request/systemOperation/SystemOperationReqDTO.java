package com.zfinance.dto.request.systemOperation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
public class SystemOperationReqDTO  implements Serializable {

    private String method;
    private String cashDeskId;
    private String serial;
    private Double amount;
    private String fullName;
}
