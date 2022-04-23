
package com.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Estratégia concreta. 
 * 
 * Implementa métodos de pagamento do PayPal.
 */
public class PayByPayPal implements PayStrategy {
    
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("amanda1985", "amanda@ya.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }

    /**
     * Coleciona os dados dos clientes.
     */
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.print("Entre o email do usuário: ");
                email = READER.readLine();
                System.out.print("Entre a senha: ");
                password = READER.readLine();
                if (verify()) {
                    System.out.println("A verificação de dados foi bem-sucedida.");
                } else {
                    System.out.println("E-mail ou senha incorretos!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * Salve os dados do cliente para futuras tentativas de compra.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Pagando " + paymentAmount + " usando PayPal.");
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}