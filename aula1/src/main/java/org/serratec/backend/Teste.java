package org.serratec.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
public class Teste {
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/{name}")
    public String helloName(@PathVariable String name){
        return "Hello " + name;
    }
}
