/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.inject.Named;

/**
 *
 * @author lukegryga
 */
@Named
public class ExchangeRateTableImpl implements ExchangeRateTable {
    
    private static final Map<CurrencyPair, BigDecimal> RATE_TABLE = new HashMap<>();
    
    static {
        RATE_TABLE.put(new CurrencyPair("EUR", "CZK"), new BigDecimal(27));
    }

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if (sourceCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        
        CurrencyPair currencyPair = new CurrencyPair(sourceCurrency, targetCurrency);
        if (!RATE_TABLE.containsKey(currencyPair)) {
            return null;
        }
        return RATE_TABLE.get(currencyPair);
    }
    
    private static class CurrencyPair {
        
        public Currency c1;
        public Currency c2;

        public CurrencyPair(String currCode1, String currCode2) {
            this(Currency.getInstance(currCode1), Currency.getInstance(currCode2));
        }

        public CurrencyPair(Currency c1, Currency c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
        
        

        @Override
        public int hashCode() {
            int hash = 7;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CurrencyPair other = (CurrencyPair) obj;
            if (!Objects.equals(this.c1, other.c1)) {
                return false;
            }
            return Objects.equals(this.c2, other.c2);
        }
    }
}