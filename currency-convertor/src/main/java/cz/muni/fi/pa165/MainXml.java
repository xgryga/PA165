
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xgryga
 */
public class MainXml {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        CurrencyConvertor currConvertor = (CurrencyConvertor)context.getBean("CurrencyConvertorImpl", CurrencyConvertor.class);
        
        BigDecimal converted = currConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.ONE);
        System.out.println(converted);
    }
}
