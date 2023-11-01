package com.zfinance.orm.systemOperation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_system_operation")
public class SystemOperation implements Serializable {
    @Id
    @Field("id")
    private String id;
    @Field("method")
    private String method;
    @Field("cash_desk_id")
    private String cashDeskId;
    @Field("serial")
    private String serial;
    @Field("amount")
    private Double amount;
    @Field("fullName")
    private String fullName;

    @Transient
    public static final String SEQUENCE_NAME = "system_Operation_sequence";
}
