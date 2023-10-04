import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReverseClass {

    public static String reverse(String src) {
        char[] temp = src.trim().toCharArray();
        String result = "";
        char space = ' ';
        char hyphen = '-';
        char comma = ',';
        char dot = '.';

        int to = 0;
        int from = -1;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == space || temp[i] == hyphen || temp[i] == comma || temp[i] == dot) {
                from = i;
                for (int j = from - 1; j >= to; j--) {
                    result = result.concat(String.valueOf(temp[j]));
                    if (j == to) {
                        to = from + 1;
                    }
                }
                result = result.concat(String.valueOf(temp[from]));
            } else if (i == temp.length - 1) {
                from = i;
                for (int j = from; j >= to; j--) {
                    result = result.concat(String.valueOf(temp[j]));
                    if (j == to) {
                        to = from + 1;
                    }
                }
            }
        }
        return result;
    }

    public static String reverse(String src, String dest) {
        char[] temp = src.trim().toCharArray();
        String result = "";
        Pattern p = Pattern.compile(dest);
        Matcher m = p.matcher(src.trim());

        while (m.find()) {
            for (int i = 0; i < temp.length; i++) {
                if (i < m.start() || i > m.end() - 1) {
                    result = result.concat(String.valueOf(temp[i]));
                } else if (i == m.start()) {
                    result = result.concat(reverse(String.valueOf(p)));
                }
            }
        }
        return result;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        char[] temp = src.trim().toCharArray();
        String result = "";

        for (int i = 0; i < temp.length; i++) {
            if (i < firstIndex || i >= lastIndex) {
                result = result.concat(String.valueOf(temp[i]));
            } else if (i == firstIndex) {
                String trimmed = src.trim().substring(firstIndex, lastIndex + 1);
                result = result.concat(reverse(trimmed));
                i = lastIndex;
            }
        }
        return result;
    }
}
