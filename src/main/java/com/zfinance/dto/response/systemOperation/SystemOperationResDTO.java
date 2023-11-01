package com.zfinance.dto.response.systemOperation;

import com.zfinance.orm.systemOperation.SystemOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SystemOperationResDTO implements Serializable {

    List<SystemOperation> records;

}
