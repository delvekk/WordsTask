package com.dawid;

import java.util.*;

public class Main {

    public static void main(String[] args) {
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
        for(Character character : textArray) {
            if(character >= 97 && character <= 122)
                charactersSet.add(character);
        }
        for(Character character : charactersSet) {
            wordsMap.put(character, new TreeSet<>());
        }
        return wordsMap;
    }

    private static void addWordsToMap(String[] wordsInUserText, Map<Character, Set<String>> wordsMap) {
        for(String word : wordsInUserText) {
            String clearedWord = word.replaceAll("[^A-Za-z]", "");
            for(Character key : wordsMap.keySet()) {
                if(clearedWord.contains(key.toString())) {
                    Set<String> wordSet = wordsMap.get(key);
                    wordSet.add(clearedWord);
                    wordsMap.put(key, wordSet);
                }
            }
        }
    }

    private static void printResults(Map<Character, Set<String>> wordsMap) {
        for(Map.Entry<Character, Set<String>> entry : wordsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
