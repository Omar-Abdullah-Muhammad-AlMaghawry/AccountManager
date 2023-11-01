package com.zfinance.services.systemOperation;

import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.systemOperation.SystemOperation;
import com.zfinance.repositories.systemOperation.SystemOperationRepository;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemOperationServiceImpl implements SystemOperationService{

    @Autowired
    SystemOperationRepository  systemOperationRepository;

    @Autowired
    SequenceGeneratorService  sequenceGeneratorService;

    @Override
    public SystemOperation createSystemOperation(SystemOperation operation) {
        String id=sequenceGeneratorService.generateSequence(SystemOperation.SEQUENCE_NAME);
        operation.setId(id);
        systemOperationRepository.save(operation);
        return operation;
    }

    @Override
    public SystemOperation updateSystemOperation(String id, SystemOperation operation) {
        Optional<SystemOperation> operationOptional = systemOperationRepository.findById(id);

        if (operationOptional.isPresent()) {
            SystemOperation systemOperation = operationOptional.get();
            return systemOperationRepository.save(operation);
        } else {
            throw new DataNotFoundException(SystemOperation.class, id);
        }
    }

    @Override
    public SystemOperation deleteSystemOperation(String id) {
        Optional<SystemOperation> operationOptional = systemOperationRepository.findById(id);

        if (operationOptional.isPresent()) {
            SystemOperation systemOperation = operationOptional.get();
            systemOperationRepository.delete(systemOperation);
            return systemOperation;

        } else {
            throw new DataNotFoundException(SystemOperation.class, id);
        }
    }

    @Override
    public List<SystemOperation> getSystemOperations() {
        return systemOperationRepository.findAll();
    }
}
