import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {


    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to reverse full string please enter 1");
        System.out.println("If you want to reverse a part of string using substring please enter 2");
        System.out.println("If you want to reverse a part of string using indexes please enter 3");
        System.out.println("If you want CLOSE app please enter 0");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> reverse(bufferedReader);
            case "2" -> reverseBySubstring(bufferedReader);
            case "3" -> reverseByIndex(bufferedReader);
            case "0" -> System.exit(0);
            default -> System.out.println("Incorrect data.Choose actual variant");
        }
    }

    private void reverse(BufferedReader reader) throws IOException {
        System.out.println("Please enter a sentence");
        String src = reader.readLine();

        System.out.println(ReverseClass.reverse(src));
    }

    private void reverseBySubstring(BufferedReader reader) throws IOException {
        System.out.println("Please enter a sentence");
        String src = reader.readLine();
        System.out.println("Please enter a substring");
        String part = reader.readLine();

        System.out.println(ReverseClass.reverse(src, part));
    }

    private void reverseByIndex(BufferedReader reader) throws IOException {
        System.out.println("Please enter a sentence");
        String src = reader.readLine();

        int firstIndex= -1;
        int lastIndex = -1;
        try {
            System.out.println("Please enter first index");
            firstIndex = Integer.parseInt(reader.readLine());
            System.out.println("Please enter last index");
            lastIndex = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data.Please enter a number");
        }

        if (lastIndex < firstIndex) {
            System.out.println("Start index should be less than last index.Try again");
        } else if (firstIndex < 0 || lastIndex > src.length()) {
            System.out.println("One or two of indexes did not exist.Try again");
        } else {
            System.out.println(ReverseClass.reverse(src, firstIndex, lastIndex));
        }
    }
}
