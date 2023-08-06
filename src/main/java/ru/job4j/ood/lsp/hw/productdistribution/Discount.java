package ru.job4j.ood.lsp.hw.productdistribution;

public class Discount {
    private double originalNumber;
    private double discountNumber;

    public Discount(double originalNumber, double discountNumber) {
        this.originalNumber = originalNumber;
        this.discountNumber = discountNumber;
    }

    public double getOriginalNumber() {
        return originalNumber;
    }

    public void setOriginalNumber(double originalNumber) {
        this.originalNumber = originalNumber;
    }

    public double getDiscountNumber() {
        return discountNumber;
    }

    public void setDiscountNumber(double discountNumber) {
        this.discountNumber = discountNumber;
    }

    public double getDiscount() {
        return this.originalNumber - (this.originalNumber * (this.discountNumber / 100));
    }
}
