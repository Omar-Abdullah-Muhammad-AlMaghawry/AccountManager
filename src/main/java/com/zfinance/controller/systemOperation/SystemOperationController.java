package com.zfinance.controller.systemOperation;


import com.zfinance.dto.request.systemOperation.SystemOperationReqDTO;
import com.zfinance.dto.response.systemOperation.SystemOperationResDTO;
import com.zfinance.mapper.SystemOperationMapper;
import com.zfinance.orm.systemOperation.SystemOperation;
import com.zfinance.services.systemOperation.SystemOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/systemOperation")
@CrossOrigin("*")
public class SystemOperationController {

    @Autowired
    SystemOperationService systemOperationService;

    @GetMapping
    public SystemOperationResDTO getAllOperation(){
        SystemOperationResDTO systemOperationResDTO= new SystemOperationResDTO();
        systemOperationResDTO.setRecords(systemOperationService.getSystemOperations());
        return systemOperationResDTO;
    }

    @PostMapping
    public SystemOperation CreateSystemOperation(@RequestBody SystemOperationReqDTO reqDto){
        return systemOperationService.createSystemOperation(
                SystemOperationMapper.INSTANCE.mapSystemOperationReqDto(reqDto));
    }

    @PatchMapping("/{id}")
    public SystemOperation updateSystemOperation(@PathVariable String id, @RequestBody SystemOperation req){
        return systemOperationService.updateSystemOperation(id,req);
    }

    @DeleteMapping("/{id}")
    public SystemOperation deleteSystemOperation(@PathVariable String id){
        return systemOperationService.deleteSystemOperation(id);
    }
}
