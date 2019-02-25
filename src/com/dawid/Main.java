package com.dawid;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj tekst: ");
        Scanner scanner = new Scanner(System.in);
        String userText = scanner.nextLine().toLowerCase();
        String[] wordsInUserText = userText.split(" ");
        char[] charactersArray = userText.toCharArray();
        Map<Character, Set<String>> wordsMap = buildMapWithKeys(charactersArray);
        addWordsToMap(wordsInUserText, wordsMap);
        printResults(wordsMap);
    }

    private static Map<Character, Set<String>> buildMapWithKeys(char[] textArray) {
        Map<Character, Set<String>> wordsMap = new HashMap<>();
        Set<Character> charactersSet = new HashSet<>();
        for (Character character : textArray) {
            if (character >= 97 && character <= 122)
                charactersSet.add(character);
        }
        charactersSet.forEach(character -> wordsMap.put(character, new TreeSet<>()));
        return wordsMap;
    }

    private static void addWordsToMap(String[] wordsInUserText, Map<Character, Set<String>> wordsMap) {
        Arrays.stream(wordsInUserText)
                .map(word -> word.replaceAll("[^A-Za-z]", ""))
                .forEach(word -> wordsMap.keySet()
                        .stream()
                        .filter(key -> word.contains(key.toString()))
                        .forEach(key -> {
                            Set<String> wordsSet = wordsMap.get(key);
                            wordsSet.add(word);
                            wordsMap.put(key, wordsSet);
                        }));

    }

    private static void printResults(Map<Character, Set<String>> wordsMap) {
        wordsMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
