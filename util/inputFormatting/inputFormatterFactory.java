package util.inputFormatting;

public class inputFormatterFactory {
    public iFormatter createInputFormatter(String request) {
        iFormatter reportGenerator = null;
        if ("PRICE".equals(request)) {
            return new priceInputFormatter();
        } else if ("DATE".equals(request)) {
            return new dateInputFormatter();
        } else if ("TIME".equals(request)) {
            return new timeInputFormatter();
        }
        return reportGenerator;
    }
}