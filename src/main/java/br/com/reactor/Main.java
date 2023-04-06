package br.com.reactor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeSimulador exchangeSimulator = new ExchangeSimulador();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite sua ordem (compra ou venda) no formato [compra/venda] [quantidade] [par de moedas], ou digite 'sair' para encerrar:");
            String input = scanner.nextLine();

            if ("sair".equalsIgnoreCase(input)) {
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            try {
                boolean isBuy = "compra".equalsIgnoreCase(parts[0]);
                if (!isBuy && !"venda".equalsIgnoreCase(parts[0])) {
                    System.out.println("Ação inválida. Deve ser 'compra' ou 'venda'.");
                    continue;
                }

                double amount = Double.parseDouble(parts[1]);
                String currencyPair = parts[2].toUpperCase();

                Order order = new Order(currencyPair, amount, isBuy);
                exchangeSimulator.submitOrder(order);
                System.out.println("Ordem enviada: " + order);

            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Tente novamente.");
            }
        }

        System.out.println("Encerrando o aplicativo...");
        scanner.close();
    }
}


