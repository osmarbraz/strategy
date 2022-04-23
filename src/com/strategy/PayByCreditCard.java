package com.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementa método de pagamento cartão de crédito.
 * 
 * Estratégias concretas implementam o algoritmo enquanto seguem
 * a interface estratégia base. A interface faz delas
 * intercomunicáveis no contexto.
 */
public class PayByCreditCard implements PayStrategy {
    
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    /**
     * Collect credit card data.
     */
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Entre o numero do cartao: ");
            String number = READER.readLine();
            System.out.print("Insira a data de validade do cartão 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Entre o código CVV: ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

          // Valida o número do cartão de crédito...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Após a validação do cartão podemos efetuar a cobrança no cartão de crédito do cliente.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Pagando " + paymentAmount + " usando cartão de credito.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}