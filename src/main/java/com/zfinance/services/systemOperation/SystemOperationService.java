package com.zfinance.services.systemOperation;

import com.zfinance.orm.systemOperation.SystemOperation;

import java.util.List;

public interface SystemOperationService {

    public SystemOperation createSystemOperation(SystemOperation operation);
    public SystemOperation updateSystemOperation(String id,SystemOperation operation);
    public SystemOperation deleteSystemOperation(String id);
    public List<SystemOperation> getSystemOperations();
}
