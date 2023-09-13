package com.abg.ibc.garage.TeamsNotifier.controller;


import com.azure.core.annotation.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/notify")
public class AuthorizationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public void getToken(@RequestParam(name = "code" ,required = false) String code,
                         @RequestParam(name="state", required = false) String state ,
                         @RequestParam(name = "session_state", required = false) String session) {

        System.out.println(session);
        System.out.println(code);
    }



}

class ABS {
    private String code;
    private String state;
    private String id_token;

    public ABS(String code, String state, String id_token) {
        this.code = code;
        this.state = state;
        this.id_token = id_token;
    }

    public ABS() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}
