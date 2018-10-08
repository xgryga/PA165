/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lukegryga
 */
@Configuration
@ComponentScan("cz.muni.fi.pa165")
public class MainAnnotation {
    
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansJSR330.xml");
        CurrencyConvertor currConvertor = (CurrencyConvertor)context.getBean("currencyConvertorImpl");
        
        BigDecimal converted = currConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.ONE);
        System.out.println(converted);
    }
    
}
