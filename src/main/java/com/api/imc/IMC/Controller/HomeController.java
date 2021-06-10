/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.imc.IMC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(value = "altura", required=false, defaultValue= "") String altura,
            @RequestParam(value = "massa", required=false, defaultValue= "") String massa
    )
    {
        ModelAndView mv = new ModelAndView("index");
        
        try {
            double alturaNumero = Double.parseDouble(altura);
            double massaNumero = Double.parseDouble(massa);
            
            double imc = (massaNumero) / (alturaNumero * alturaNumero);
            String imcPalavra = String.valueOf(imc);
                    
            if (imc < 18.50) {
                mv.addObject("classificacao", "Abaixo do Peso");
                mv.addObject("risco", "Elevado");

            } else if (imc < 24.99) {
                mv.addObject("classificacao", "Peso Ideal");
                mv.addObject("risco", "Inexistente");                
            } else if (imc < 29.99){
                mv.addObject("classificacao", "Excesso de Peso");
                mv.addObject("risco", "Elevado");            
            } else if (imc < 34.99){
                mv.addObject("classificacao", "Obesidade Grau 1");
                mv.addObject("risco", "Muito Elevado");            
            } else if (imc < 39.99) {
                mv.addObject("classificacao", "Obesidade Grau 2");
                mv.addObject("risco", "Muitíssimo Elevado");            
            } else if (imc > 40){
                mv.addObject("classificacao", "Obesidade Grau 3");
                mv.addObject("risco", "Obesidade Mórbida");            
            }
            
            mv.addObject("imc", imc);

        } catch (Exception e) {
        }
        

        return mv;
    }
    
}
