package util.inputFormatting;

import javax.swing.JTextField;

public class inputFormatterFactory {
    public iFormatter createInputFormatter(String request) {
        if ("PRICE".equals(request)) {
            return new priceInputFormatter();
        } else if ("DATE".equals(request)) {
            return new dateInputFormatter();
        } else if ("TIME".equals(request)) {
            return new timeInputFormatter();
        }
        return new dummyFormatter();
    }

    private class dummyFormatter implements iFormatter {

        public void applyFormat(JTextField theTextField) {
        }

        public boolean isFilled(JTextField theTextField) {
            return false;
        }

    }
}