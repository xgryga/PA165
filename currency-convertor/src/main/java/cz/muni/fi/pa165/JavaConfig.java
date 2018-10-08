/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author lukegryga
 */
@Configuration
@EnableAspectJAutoProxy
public class JavaConfig {
    
//    @Bean
//    public LoggingAspect loggingAspect() {
//        return new LoggingAspect();
//    }
    
    @Bean
    public ExchangeRateTable exchangeRateTable() {
        return new ExchangeRateTableImpl();
    }
    
    @Bean
    public CurrencyConvertorImpl currencyConvertorImpl() {
        return new CurrencyConvertorImpl(exchangeRateTable());
    }
}
