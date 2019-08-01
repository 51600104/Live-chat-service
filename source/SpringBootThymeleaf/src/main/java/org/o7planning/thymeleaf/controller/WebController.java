package org.o7planning.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class WebController {
	
    @GetMapping(value="/Admin")
    public String adminpage(){
        return "Adminside";
    }
    
    
    @GetMapping(value="/Client")
    public String clientpage(){
        return "Clientside";
    }
    
}