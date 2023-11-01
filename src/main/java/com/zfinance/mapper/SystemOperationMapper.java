package com.zfinance.mapper;

import com.zfinance.dto.request.systemOperation.SystemOperationReqDTO;
import com.zfinance.orm.systemOperation.SystemOperation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemOperationMapper {

    SystemOperationMapper INSTANCE = Mappers.getMapper(SystemOperationMapper.class);
    public SystemOperation mapSystemOperationReqDto(SystemOperationReqDTO  operationReqDTO);
}
