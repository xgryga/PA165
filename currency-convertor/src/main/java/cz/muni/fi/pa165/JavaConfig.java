/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
//@Aspect
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
    
    @Around("execution(* cz.muni.fi.pa165.currency.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
            long start = System.nanoTime();
            System.out.println("Enering: " + pjp.getSignature().getName());
            Object output = pjp.proceed();
            System.out.println("Method execution completed.");
            long elapsedTime = System.nanoTime() - start;
            System.out.println("Method execution time: " + elapsedTime + " nanosecond");
            return output;
    }
}
