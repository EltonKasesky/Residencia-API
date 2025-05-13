package com.vagabundo.revisao.controller;

import com.vagabundo.revisao.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //Permite que minha classe utilize os metodos do protocolo HTTP
@RequestMapping("/hello") //Mapeia meu endpoint para acessar os metodos/classe
public class HelloController {
    @Autowired
    private HelloService helloService;

    //Metodo
    @GetMapping //Indica que é o metodo HTTP GET -> Pega um valor do servidor
    private String helloWorld(){
        return "Hello World";
    }

    //Metodo
    @PostMapping("/{name}") //Indica que é o metodo HTTP POST (Envia um dado) - Ex: localhost:8080/hello/Elton
    private String helloName(@PathVariable String name){
        return "Hello " + name;
    }

    //Metodo
    @PostMapping("/value/{v}") // localhost:8080/hello/value/5
    public String value(@PathVariable Integer v){
        return helloService.value(v);
    }
}