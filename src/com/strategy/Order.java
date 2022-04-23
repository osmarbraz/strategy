package com.strategy;

/**
 * Aula de pedidos. 
 * 
 * Não conhece o método de pagamento concreto (estratégia) que o usuário tem
 * escolhido. Ele usa uma interface de estratégia comum para delegar a coleta 
 * de dados de pagamento ao objeto de estratégia. Ele pode ser usado para 
 * salvar o pedido no banco de dados.
 */
public class Order {
    
    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
        // Aqui podemos coletar e armazenar dados de pagamento da estratégia.
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}