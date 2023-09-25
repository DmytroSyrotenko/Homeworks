package java8.hw6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        bufferedReader.lines();

        String input = "This is some, dummy text. This is dummy dummy text. Dummy text";
        System.out.println("input: " + input);

        List<String> words = new ArrayList<>(Arrays.asList(input.toLowerCase().replace(".", "").replace(",", "").split(" ")));
        System.out.println("words :" + words);
        List<String> sentences = new ArrayList<>((Arrays.asList(input.toLowerCase().replaceAll(",", "").replaceAll("\\s+", ",").split("\\."))));
        System.out.println("sentences :" + sentences);

        List<RatingBox> listOfObjects = words
                .stream()
                .distinct()
                .map(RatingBox::new)
                .peek(ratingBox ->
                        ratingBox.setCount(words
                                .stream()
                                .filter(e -> e.equals(ratingBox.getWord())).count()))
                .peek(ratingBox ->
                        ratingBox.setPercentageAll(Math.floor(((double) ratingBox.getCount() / words.size()) * 100)))

                .sorted(Comparator.comparingLong(RatingBox::getCount).reversed())
                .toList();

        for (RatingBox listOfObject : listOfObjects) System.out.println("listOfObjects " + listOfObject);

        List<RatingBox> setRatingList = listOfObjects
                .stream()
                .peek(ratingBox -> ratingBox.setPercentageAll(25))
                .peek(ratingBox -> ratingBox.setRating((listOfObjects.indexOf(ratingBox)) + 1))
                .toList();
        System.out.println("setRatingList" + setRatingList);

        sentences.stream()
                .map(Object::toString)
                .forEach(System.out::println);

        System.out.println(sentences.get(0).length());
        System.out.println(sentences.get(1).length());
        System.out.println(sentences.get(2).length());
        System.out.println(sentences.get(2).length());
    }

    public static void printTopOfTable() {
        System.out.println("|-----------------|--------|--------|-------------|-------------|");
        System.out.println("|      Word       | Rating |  Count |  % of total |% in sentence|");
        System.out.println("|-----------------|--------|--------|-------------|-------------|");
    }

    public static void printBottomOfTable() {
        System.out.println("|-----------------|--------|--------|-------------|-------------|");

    }
}


//        Map<String,Long> map = inputArray.stream()
//                .collect(Collectors.groupingBy(o -> o,
//                        Collectors.counting()));
//
//
//        System.out.println(map);