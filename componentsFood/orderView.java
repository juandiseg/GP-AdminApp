package componentsFood;

public class orderView {

    private int totalTimeMin;
    private float subtotal;
    private String paymentMethod;

    public orderView(int totalTimeMin, float subtotal, String paymentMethod) {
        this.totalTimeMin = totalTimeMin;
        this.subtotal = subtotal;
        this.paymentMethod = paymentMethod;
    }

    public int getTotalTimeMinutes() {
        return totalTimeMin;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
