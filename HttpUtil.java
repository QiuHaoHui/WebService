package client;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.demo.DemoApplication.sendEmail;
import static com.example.demo.DemoApplication.sendEmailBatch;


public class HttpUtil {
     JPanel p;
    public static  JTextField j1;
    public static  JTextField j2;

    HttpUtil(){
        JFrame frame =new JFrame();
        frame.setTitle("邮件发送");
        JPanel p=new JPanel();
        frame.getContentPane().add(p);
        JTextField j1 = new JTextField(25);
        JLabel Label1 = new JLabel("收件人:");
        p.add(Label1);
        p.add(j1);
        JTextField j2 = new JTextField(25);
        JLabel Label2 = new JLabel("内容:");
        p.add(Label2);
        p.add(j2);

        JButton button1=new JButton("单发");
        JButton button2=new JButton("群发");
        p.add(button1);
        p.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                if(e.getActionCommand().equals("单发")){
                    String a=j1.getText();
                    String b=j2.getText();
                    sendEmail(a,b);
                    System.out.println("发送成功");
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                if(e.getActionCommand().equals("群发")){
                    String x=j1.getText();
                    String y=j2.getText();
                    sendEmailBatch(x,y);
                    System.out.println("发送成功");
                }
            }
        });

        frame.add(p);
        frame.setLayout(new FlowLayout());
        frame.setSize(1200,300);
        frame.setVisible(true);
    }
   public static String JsonSMS(String postData, String postUrl) {



        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

    public static void main(String[] args) {
        //发送 POST 请求
        String postData = "";
        String sr=HttpUtil.JsonSMS(postData,"http://localhost:8080/mail");
        System.out.println(sr);
        new HttpUtil();

    }


}
