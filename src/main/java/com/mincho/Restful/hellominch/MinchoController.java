package com.mincho.Restful.hellominch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
public class MinchoController {
    //(Minch = endpoint)
    @GetMapping("/Minch")
    public String MinchHello(){
        return "MinchHello";
    }

    @GetMapping("/Himinch")
    public MinchoBean Himinch(){
       return new MinchoBean("Hi");
    }
    @GetMapping("/Himinch/path-variable/{name}")
    public MinchoBean Himinch(@PathVariable String name){
        return new MinchoBean(String.format("hellominch,%s",name));
    }
}
