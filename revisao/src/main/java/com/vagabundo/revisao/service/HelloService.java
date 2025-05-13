package com.vagabundo.revisao.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String value(Integer a){
        return "O valor é: " + a;
    }
}
