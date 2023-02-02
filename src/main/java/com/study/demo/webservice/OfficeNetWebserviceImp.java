package com.study.demo.webservice;

import com.study.demo.vo.SecurityCommmandVo;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author caonan
 * @Date 2022/8/12 14:05
 * @Description
 */
@WebService(name = "OfficeNetWebservice",endpointInterface = "com.study.demo.webservice.OfficeNetWebservice")
@Component
public class OfficeNetWebserviceImp implements OfficeNetWebservice {


    @Override
    public String getCommand(SecurityCommmandVo securityCommmandVo) {
        return "<?xml version='2.0' encoding='UTF-8'?>" +
                "<feedback>" +
                "  <commandAck>" +
                "  <commandId>XXXXX</commandId>" +
                "  <type>0101</type>" +
                "  <resultCode>0</resultCode>" +
                "  <msgInfo>XXXXX</msgInfo>" +
                "  </commandAck>" +
                "  <timeStamp>2021-06-29 11:12:52</timeStamp>" +
                "</feedback>";
    }

    @Override
    public String getCommand1(@WebParam(name = "odId",targetNamespace = "http://webservice.demo.study.com/") String odId, @WebParam(name = "randVal")String randVal,@WebParam(name = "timestamp")String timestamp,
                              @WebParam(name = "pwdHash")String pwdHash, @WebParam(name = "command")String command,@WebParam(name = "commandHash")String commandHash,
                              @WebParam(name = "commandType")String commandType,  @WebParam(name = "commandSequence")String commandSequence,
                              @WebParam(name = "encryptAlgorithm")String encryptAlgorithm,@WebParam(name = "hashAlgorithm")String hashAlgorithm,
                              @WebParam(name = "compressionFormat")String compressionFormat, @WebParam(name = "commandVersion")String commandVersion) {
        return null;
    }
}
