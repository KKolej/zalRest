package com.kolej.bartosz.zalrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class TestController {
    @GetMapping("/getTestNoAuth")
    public String getTestNoAuth() {
        System.out.println("getTestNoAuth");
        return "getTestNoAuth";
    }

    @GetMapping("/getTestAuthUser")
    public String getTestAuthUser() {
        System.out.println("getTestAuthUser");
        return "getTestAuthUserx";
    }
}
