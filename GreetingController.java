package com.example.demo;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.DemoApplication.sendEmail;

@RestController
public class GreetingController {
    @RequestMapping("/mail")
    String home(){
        String address="1797887724@cug.edu.cn";
        String data="hello!";
        if(!sendEmail(address,data))
            return "N";
        return "Y";
    }

    @RequestMapping("/{address}&{data}/mail")
    String home1(@PathVariable String address,@PathVariable String data){
        if(!sendEmail(address,data))
            return "N";
        return "Y";
    }

    @RequestMapping("/EmailBatch")
    String home2(@PathVariable String[] _url,@PathVariable String data){

        return "Y";
    }


}


