package com.study.demo.webservice;

import com.study.demo.vo.SecurityCommmandVo;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.*;

@WebService(name = "OfficeNetWebservice",targetNamespace = "http://webservice.demo.study.com/")
@Component
public interface OfficeNetWebservice {
    /**
     *
     * @param odId
     * @param randVal
     * @param timestamp
     * @param pwdHash
     * @param command
     * @param commandHash
     * @param commandType
     * @param commandSequence
     * @param encryptAlgorithm
     * @param hashAlgorithm
     * @param compressionFormat
     * @param commandVersion
     * @return
     */
    @WebMethod(operationName = "security_commandack",action = "commandack")
    public String getCommand(SecurityCommmandVo securityCommmandVo);
    @WebMethod(operationName = "security_commandack1",action = "commandack")
    public String getCommand1(@WebParam(name = "odId",targetNamespace = "http://webservice.demo.study.com/")  String odId,
                              @WebParam(name = "randVal",targetNamespace = "http://webservice.demo.study.com/")String randVal,
                              @WebParam(name = "timestamp",targetNamespace = "http://webservice.demo.study.com/")String timestamp,
                              @WebParam(name = "pwdHash",targetNamespace = "http://webservice.demo.study.com/")String pwdHash,
                              @WebParam(name = "command",targetNamespace = "http://webservice.demo.study.com/")String command,
                              @WebParam(name = "commandHash",targetNamespace = "http://webservice.demo.study.com/")String commandHash,
                              @WebParam(name = "commandType",targetNamespace = "http://webservice.demo.study.com/")String commandType,
                              @WebParam(name = "commandSequence",targetNamespace = "http://webservice.demo.study.com/")String commandSequence,
                              @WebParam(name = "encryptAlgorithm",targetNamespace = "http://webservice.demo.study.com/")String encryptAlgorithm,
                              @WebParam(name = "hashAlgorithm",targetNamespace = "http://webservice.demo.study.com/")String hashAlgorithm,
                              @WebParam(name = "compressionFormat",targetNamespace = "http://webservice.demo.study.com/")String compressionFormat,
                              @WebParam(name = "commandVersion",targetNamespace = "http://webservice.demo.study.com/")String commandVersion);
}
