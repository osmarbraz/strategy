package com.strategy;

/**
 * Interface comum para todas as estratégias.
 * 
 * A interface estratégia declara operações comuns a todas as
 * versões suportadas de algum algoritmo. O contexto usa essa
 * interface para chamar o algoritmo definido pelas estratégias
 * concretas.
 */
public interface PayStrategy {
    
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
