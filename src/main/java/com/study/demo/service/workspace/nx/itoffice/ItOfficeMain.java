package com.study.demo.service.workspace.nx.itoffice;

import org.apache.axis.AxisFault;
import org.apache.axis.AxisProperties;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.text.SimpleDateFormat;

/**
 * @author caonan
 * @createtime 2023/2/7 17:58
 * @Description 宁夏移动IT办公网测试
 * @Version 1.0
 */
public class ItOfficeMain {
    private static final Logger log = LoggerFactory.getLogger(WebServiceUtil.class);
    private static final String AES_KEY = "LVVKVXWF56496929";
    private static String AES_OFFSET = "MSFBUWFS86537785";
    // 用户令牌，以实际为准
    private static String token = "d5729wert086qw3267basdfgetee731b2c";
    // 消息认证秘钥，以实际为准
    private static String SECRET_KEY = "SUnEzHVo6XJFoGKZq5WM";

    private static void sendWebservice(){
        Call call = null;
        try {
            Service service = new Service();

            call = (Call) service.createCall();
            //设置地址
//            call.setTargetEndpointAddress(ConfigUtil.get(ITOfficeConfig.class).getCommandUrl());
            call.setTargetEndpointAddress("http://127.0.0.1:16520/sap/SecurityWebService/SecurityCommand?wsdl");
            AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
            call.setUseSOAPAction(true);
            //设置要调用哪个方法
//            String commandQname = ConfigUtil.get(ITOfficeConfig.class).getCommandQname();
            String commandQname = "http://controller.cascade.brick.cybertron.cn/";
//            call.setOperationName(new QName(commandQname, "security_command"));
            call.setOperationName(new QName(commandQname, "security_command"));
            call.setEncodingStyle("");
            //设置返回类型
            call.setReturnType(XMLType.XSD_STRING);
            call.addParameter("odId", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("randVal", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("timestamp", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("pwdHash", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("command", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("commandHash", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("commandType", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("commandSequence", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("encryptAlgorithm", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("hashAlgorithm", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("compressionFormat", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("commandVersion", XMLType.XSD_STRING, ParameterMode.IN);
            String res = (String) call.invoke(new Object[]{"01026400003", "drblXPDuUjSXBpaJYyGV", "2022-12-19 14:25:02", "Njc0NTA4NTU1MWRiMDdhNzkzMjNhMmRiZDE0ODc3YmQ",
                    "pxdnkdi1Fpy40WxBEC7PYDBb1s1Gpx/Ag86FLK/66MczzQm8W+3CuGmKTPpTxBwq+0jvzTiFfoIijGhnYmLfeftdR/rZQ0jCj+5CDF9E8bTEfk0mH+GquMIemKX4T5RLpE+YCAsz/Yx8tXTag3QIGNcxST5oARZMnJ4PJEBnrcp2xDhyte1ycAe+tbeJ7fDaHJTIeGcy2njjVlVoWCS9VJqy+xYCLMKnwMF+AZ91gskeyCmCj+kDphFpPcrB+qFq5AqJE74NG3LGatsuI8E8tNh+Bo8bZgRqhBOZ4mpvcuxlB4LxKJalGh9sNqajvK7dDiXRgkyQhCNM1Z2Dj1MTUo1CI9NFejwhakwUFJpdfc2X5JtrtagxUxMAwCSG0Yw4yl4SA15LTww2MaKjSBaglX/LeFc491QpApjduXnbLjLookkjKHsW+Tvotks+HLHVPQelZgXbEb2tHn315bgSQwFr+2F7vszTVhGsj0Nrr+tKtsX3+n8aXbFMfCSmKJtL9eXxwfehrMr7kQW8q6zFjllt1Xg+FwS+hVbZuvwSwdO7l8O3PwE4tiScT16KGsUrmpF83jaqY2TfnVFqVN/3zVDDxgbKOXocoxaqHgN7OGV67PVZwSBvMCo4R0Z85b10vWE/9y4cVDXzNYRwV9a3gEGRswJiqmK3zGWrANU2EqEuCaR23nrJeS5zM/Gsj+GnPM+aizRfM4Sz5L/2wYTVLkrBpOqKZgFMAZWGaDMvcp8iVPDzUz3/09Kq7HuPgqskzkKg3SQXeZ2toRpSrSKJWUdkrrgOmYVNvGAtVbI0auOjLD1ZI+HbNDG0ZRyoPO4WGJfzNHRgIuAcUmYB+bn7r3gmCtwT7kFyNoN1Ts5qw/asVqvHT5aj81+7oK4Nw3ecitu/gjmg7Jee4K9eqnjrQg==",
                    "NTY5MjExMzdkNDZkOWFhZTU1MzE4YzFhOGJkNDE5NzA", "0401",
                    "13040164001206", 1, 1, 1, "2.0"});
        } catch (Exception e) {
            String soapPartAsString = null;
            try {
                soapPartAsString = call.getMessageContext().getRequestMessage().getSOAPPartAsString();
            } catch (AxisFault axisFault) {
                axisFault.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public static void decryptSecurityCommand(String odId, String randVal, String timeStamp, String pwdHash, String command,
                                              String commandHash, String commandType, String commandSequence, Integer encryptAlgorithm,
                                              Integer hashAlgorithm, Integer compressionFormat,
                                              String commandVersion) {
        // timeStamp校验

        // 校验pwdHash值
        String pwdHashCalc = WebServiceUtil.getPwdHash(randVal, token, timeStamp, hashAlgorithm);
        if (!pwdHashCalc.equals(pwdHash)) {
            log.error("pwdHash校验失败");
            return;
        }
        // 校验commandHash
        byte[] dataOfDecryptCommand = WebServiceUtil.dataOfDecryptCommand(command, encryptAlgorithm, AES_KEY, AES_OFFSET);
        String commandHashCalc = WebServiceUtil.getCommandHash(dataOfDecryptCommand, SECRET_KEY, hashAlgorithm);
        if (!commandHash.equals(commandHashCalc)) {
            log.error("commandHash校验失败");
            return;
        }
        // 获取command
        String originCommand = WebServiceUtil.getOriginCommand(dataOfDecryptCommand, compressionFormat);//解压缩
        log.info("解析到指令内容，结束originCommand"+originCommand);

    }
    public static void getSecurityCommand(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        String odId = "01026400003";
//        String randVal = WebServiceUtil.getRandomString(20);
        String randVal = "drblXPDuUjSXBpaJYyGV";
//        String timeStamp = sdf.format(System.currentTimeMillis());
        String timeStamp = "2022-12-19 14:25:02";
        Integer hashAlgorithm = 1;//TODO 0：无hash；1：MD5；2：SHA-1；
        Integer encryptAlgorithm = 1;//TODO 0：不进行加密，明文传输； 1：AES加密算法。
        Integer compressionFormat = 1;//TODO 0：无压缩；1：tar.gz压缩格式
        String commandVersion = "2.0";
        String pwdHash = WebServiceUtil.getPwdHash(randVal,token,timeStamp,hashAlgorithm);
        String originCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<command> \n" +
                "  <commandInfo> \n" +
                "    <commandPriority>1</commandPriority>  \n" +
                "    <commandSource>1</commandSource>  \n" +
                "    <timeStamp>2022-12-19 14:30:13</timeStamp>  \n" +
                "    <owner>sysadmin</owner>  \n" +
                "    <version>2.0</version>  \n" +
                "    <operationType>0</operationType>  \n" +
                "    <sourceSystem>3</sourceSystem>  \n" +
                "    <commandID>13040164001206</commandID> \n" +
                "  </commandInfo>  \n" +
                "  <commandResult> \n" +
                "    <handleType>1</handleType>  \n" +
                "    <reportType>0</reportType>  \n" +
                "    <reportCycle>3</reportCycle> \n" +
                "  </commandResult>  \n" +
                "  <commandRule> \n" +
                "    <commandType>0401</commandType>  \n" +
                "    <rule> \n" +
                "      <eventLevel>1</eventLevel>  \n" +
                "      <applicationProtocol>999</applicationProtocol>  \n" +
                "      <ruleType>1</ruleType>  \n" +
                "      <ruleID>13040164001206</ruleID>  \n" +
                "      <protocolType>1</protocolType>  \n" +
                "      <eventType>011600</eventType>  \n" +
                "      <srcPort/>  \n" +
                "      <ruleDescription>查询历史安全日志</ruleDescription>  \n" +
                "      <destIp>218.203.141.152</destIp>  \n" +
                "      <startTime>2022-12-17 12:52:49</startTime>  \n" +
                "      <srcIp>10.224.7.114</srcIp>  \n" +
                "      <isUploadFile>0</isUploadFile>  \n" +
                "      <endTime>2022-12-19 14:00:00</endTime>  \n" +
                "      <destPort/> \n" +
                "    </rule> \n" +
                "  </commandRule>  \n" +
                "  <commandObject> \n" +
                "    <effectVendor/>  \n" +
                "    <effectSystem>3</effectSystem>  \n" +
                "    <effectOperator>0102</effectOperator>  \n" +
                "    <effectHouse/>  \n" +
                "    <effectProvince>640000</effectProvince> \n" +
                "  </commandObject> \n" +
                "</command>";//TODO 下发指令
        String commandType = "0401";//TODO 下发指令里面找"指令类型"
        String commandSequence = "13040164001206";//TODO 下发指令里面的指令下发唯一编码
        // 压缩  compressionFormat加压缩判定
        // 加密 encryptAlgorithm加密  加密所需参数AES_KEY AES_OFFSET
        // base64编码
        String command = WebServiceUtil.readCommand(originCommand,compressionFormat,encryptAlgorithm,AES_KEY,AES_OFFSET);
        byte[] dataOfDecryptCommand = WebServiceUtil.dataOfDecryptCommand(command, encryptAlgorithm, AES_KEY, AES_OFFSET);
        String commandHash = WebServiceUtil.getCommandHash(dataOfDecryptCommand, SECRET_KEY, hashAlgorithm);
        decryptSecurityCommand(odId, randVal, timeStamp, pwdHash, command, commandHash, commandType,
                commandSequence, encryptAlgorithm, hashAlgorithm, compressionFormat, commandVersion);
    }

    public static void main(String[] args) {
        getSecurityCommand();

    }
}
