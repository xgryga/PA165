/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author lukegryga
 */
@Aspect
public class LoggingAspect {
    
    @After("execution(* cz.muni.fi.pa165.currency.*(..))")
    public void logBefore(JoinPoint joinPoint) {

		System.out.println("logged before: " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
}
