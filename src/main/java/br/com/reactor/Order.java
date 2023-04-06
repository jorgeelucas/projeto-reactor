package br.com.reactor;

public class Order {
    private String currencyPair;
    private double amount;
    private boolean isBuy;

    public Order(String currencyPair, double amount, boolean isBuy) {
        this.currencyPair = currencyPair;
        this.amount = amount;
        this.isBuy = isBuy;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isBuy() {
        return isBuy;
    }

    @Override
    public String toString() {
        return (isBuy ? "Compra" : "Venda") + " de " + amount + " " + currencyPair;
    }
}
