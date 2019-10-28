package com.example.demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class DemoApplication {

    public static void main(String []args){
        /*String _payload=null;
        String _url = null;
        sendEmail(_url,_payload);
        sendEmailBatch(_url,_payload);
        validateEmailAddress(_url);*/
        System.out.println("连接成功");
    }


    public static boolean sendEmail(String address,String data){
       if(!validateEmailAddress(address))
            return false;
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrNCUEswiDbGtGoCqZa", "YdslRherCgVgyu82GTkTqtSYUt8Vy3");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("lkl@likanglin.work");
            request.setFromAlias("moon");
            request.setAddressType(1);
            request.setTagName("lkl");
            request.setReplyToAddress(true);
            request.setToAddress(address);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("xxx");
            request.setHtmlBody(data);
            //开启需要备案，0关闭，1开启
            //request.setClickTrace("0");
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
        catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
        return true;
    }

    public static boolean sendEmailBatch(String address, String data){
        String[] str=address.split(";");
        String s;
        for (int i = 0; i < str.length; i++){
            s=str[i];
            System.out.println(str[i]);
            sendEmail(s,data);
        }
        return true;
    }

    private static boolean validateEmailAddress(String address) {
        Pattern p=Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        Matcher m=p.matcher(address);
        return m.matches();
    }


}
