
import com.strategy.Order;
import com.strategy.PayByCreditCard;
import com.strategy.PayByPayPal;
import com.strategy.PayStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Primeiro aplicativo de e-commerce de console do mundo.
 */
public class Principal {
    
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Por favor, selecione um produto:" + "\n" +
                        "1 - Placa mae" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memoria" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Quantidade: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Deseja continuar selecionando produtos? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("Por favor, selecione uma forma de pagamento:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Cartao de credito");
                String paymentMethod = reader.readLine();

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            // Delega o objeto de pedido coletando dados de pagamento para o objeto de estratégia,
            // já que apenas as estratégias sabem quais dados precisam para processar um
            // Forma de pagamento.
            order.processOrder(strategy);

            System.out.print("Pague " + order.getTotalCost() + " unidades ou Continuar comprando? P/C: ");
            String proceed = reader.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                // Finalmente, a estratégia trata do pagamento.
                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("O pagamento foi bem-sucedido.");
                } else {
                    System.out.println("FALHOU! Por favor, verifique seus dados.");
                }
                order.setClosed();
            }
        }
    }
}