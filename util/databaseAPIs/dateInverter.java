package util.databaseAPIs;

public class dateInverter {

    public static String invert(String date) {
        if (date.charAt(2) == '-')
            return toYearFirst(date);
        return toDayFirst(date);
    }

    private static String toYearFirst(String date) {
        char[] temp = { ' ', ' ', ' ', ' ', '-', ' ', ' ', '-', ' ', ' ' };
        temp[0] = date.charAt(6);
        temp[1] = date.charAt(7);
        temp[2] = date.charAt(8);
        temp[3] = date.charAt(9);
        temp[5] = date.charAt(3);
        temp[6] = date.charAt(4);
        temp[8] = date.charAt(0);
        temp[9] = date.charAt(1);
        return new String(temp);
    }

    private static String toDayFirst(String date) {
        char[] temp = { ' ', ' ', '-', ' ', ' ', '-', ' ', ' ', ' ', ' ' };
        temp[0] = date.charAt(8);
        temp[1] = date.charAt(9);
        temp[3] = date.charAt(5);
        temp[4] = date.charAt(6);
        temp[6] = date.charAt(0);
        temp[7] = date.charAt(1);
        temp[8] = date.charAt(2);
        temp[9] = date.charAt(3);
        return new String(temp);
    }

}
