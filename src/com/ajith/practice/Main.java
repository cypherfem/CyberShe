package com.ajith.practice;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your sentence:");
        String userText = scanner.nextLine().toLowerCase();
        ArrayList<String> appearanceWords = new ArrayList<String>();
        appearanceWords.add("beautiful");
        appearanceWords.add("skinny");
        appearanceWords.add("fat");
        appearanceWords.add("ugly");
        String[] userWords = userText.split(" ");
        for (String w : userWords) {
            if (appearanceWords.contains(w)) {
                System.out.println("Biased language against women: " + w);
            }
        }
    }
}