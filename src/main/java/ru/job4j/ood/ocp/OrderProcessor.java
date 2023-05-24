package ru.job4j.ood.ocp;

/*Использование конкретных классов вместо абстракций*/
public class OrderProcessor {
    public void processOrder(String paymentType) {
        if (paymentType.equals("creditCard")) {
            /*CreditCardPaymentProcessor paymentProcessor = new CreditCardPaymentProcessor();
            обработка платежа кредитной картой*/
        } else if (paymentType.equals("paypal")) {
            /*PayPalPaymentProcessor paymentProcessor = new PayPalPaymentProcessor();
            обработка платежа через PayPal*/
        }
    }
}
