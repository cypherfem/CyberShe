package com.ajith.practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your sentence, or type exit to quit:");
            String userText = scanner.nextLine().toLowerCase();
            if (userText.equals("exit")) {
                break;
            }
            analyzeSentence(userText);




        }
        scanner.close();


        System.out.println("Program ended.");
    }

    public static void analyzeSentence(String userText) {
        ArrayList<String> flaggedWords = createFlaggedWords();
        String[] userWords = userText.split(" ");

        System.out.println();
        System.out.println("--- Analysis Result ----");
        ArrayList<String> powerWords = createPowerwords();
        ArrayList<String> emotionalWords = createEmotionalwords();
        ArrayList<String> appearanceWords = createAppearancewords();
        ArrayList<String> genderWords = createGenderwords();

        int appearanceCount = checkWords(userWords, appearanceWords, "Appearance");
        int emotionalCount = checkWords(userWords, emotionalWords, "Emotional/support/nurturing");
        int skillCount = checkWords(userWords, powerWords, "Power/skill/brains");
        int genderCount = checkWords(userWords, genderWords, "Gender pronouns");
        int flaggedCount = checkWords(userWords, flaggedWords, "May be biased");

        System.out.println("-- BIAS AGAINST WOMEN COUNT ---");
        System.out.println("Appearance count: " + appearanceCount);
        System.out.println("Potentially biased word count: " + flaggedCount);
        System.out.println("Emotional/support count: " + emotionalCount);
        System.out.println("Skill and Power count: " + skillCount);
        System.out.println("Gender pronoun count: " + genderCount);
        int finalBiasCount = calculateBiasScore(
                appearanceCount,
                genderCount,
                skillCount,
                flaggedCount
        );

        System.out.println("Bias score: " + finalBiasCount);

        String biasLevel = getBiasLevel(finalBiasCount);

        String diagnosis = printDiagnosis(
                appearanceCount,
                emotionalCount,
                skillCount,
                flaggedCount,
                genderCount
        );

        System.out.println();


        String newText = rewriteWords(userText);

        printSummary(biasLevel, diagnosis,  userText, newText);

    }

    public static int checkWords(String[] userWords, ArrayList<String> wordList, String categoryName) {
        boolean foundAny = false;
        int count = 0;
        System.out.println(categoryName + " words found:");

        for (String w : userWords) {
            w = w.replaceAll("[^a-z]", "");

            if (wordList.contains(w)) {
                count++;
                foundAny = true;
                System.out.println("- " + w);


            }
        }
        if (!foundAny) {
            System.out.println("No words found.");
        }

        return count;
    }

    public static String rewriteWords(String userText) {
        int rewriteCount = 0;
        String[] newText = userText.split(" ");

        for (int i = 0; i < newText.length; i++) {
            String cleanWords = newText[i].replaceAll("[^a-z']", "");
            if (cleanWords.equals("chick")) {
                rewriteCount++;
                newText[i] = "woman";
            }

            if (cleanWords.equals("bossy")) {
                rewriteCount++;
                newText[i] = "assertive";
            }
        }

        if (rewriteCount > 0) {
            System.out.println("Rewrite changes made: " + rewriteCount);
        }

        return String.join(" ", newText);
    }




    public static ArrayList<String> createAppearancewords() {

        ArrayList<String> appearanceWords = new ArrayList<String>();
        appearanceWords.add("beautiful");
        appearanceWords.add("pretty");
        appearanceWords.add("short");
        appearanceWords.add("skinny");
        appearanceWords.add("ugly");
        appearanceWords.add("attractive");
        appearanceWords.add("chick");
        return appearanceWords;
    }

    public static ArrayList<String> createPowerwords() {

        ArrayList<String> powerWords = new ArrayList<String>();
        powerWords.add("skilled");
        powerWords.add("technical");
        powerWords.add("smart");
        powerWords.add("leader");
        powerWords.add("confident");
        powerWords.add("boss");
        powerWords.add("assertive");
        powerWords.add("clever");
        powerWords.add("sharp");
        powerWords.add("powerful");
        powerWords.add("expert");
        powerWords.add("engineer");
        powerWords.add("mathematician");
        powerWords.add("capable");

        return powerWords;
    }

    public static ArrayList<String> createEmotionalwords() {

        ArrayList<String> emotionalWords = new ArrayList<String>();
        emotionalWords.add("kind");
        emotionalWords.add("helpful");
        emotionalWords.add("sensitive");
        emotionalWords.add("caring");
        emotionalWords.add("sweet");
        emotionalWords.add("mature");
        emotionalWords.add("gentle");
        emotionalWords.add("loving");
        emotionalWords.add("submissive");
        emotionalWords.add("bossy");
        emotionalWords.add("calculated");
        emotionalWords.add("nurturing");

        return emotionalWords;
    }
    public static ArrayList<String> createFlaggedWords() {
        ArrayList<String> flaggedWords = new ArrayList<>();

        flaggedWords.add("bossy");
        flaggedWords.add("chick");
        flaggedWords.add("submissive");
        flaggedWords.add("hysterical");
        flaggedWords.add("overemotional");

        return flaggedWords;
    }
    public static ArrayList<String> createGenderwords() {

        ArrayList<String> genderWords = new ArrayList<String>();
        genderWords.add("she");
        genderWords.add("her");
        genderWords.add("hers");
        genderWords.add("woman");
        genderWords.add("women");
        genderWords.add("girl");
        genderWords.add("girls");
        genderWords.add("female");
        genderWords.add("lady");
        genderWords.add("ms");
        genderWords.add("mrs");
        genderWords.add("ladies");

        return genderWords;
    }

    public static int calculateBiasScore(
            int appearanceCount,
            int genderCount,
            int skillCount,
            int flaggedCount) {

        if (genderCount == 0) {
            return 0;
        }

        int finalBiasCount = flaggedCount * 2;

        if (appearanceCount > 0 && skillCount == 0) {
            finalBiasCount++;
        }

        return finalBiasCount;
    }

    public static String getBiasLevel(int finalBiasCount) {
        if (finalBiasCount <= 0) {
            return "Low";
        } else if (finalBiasCount <= 3) {
            return "Medium";
        } else {
            return "High";
        }

    }

    public static String printDiagnosis(
            int appearanceCount,
            int emotionalCount,
            int skillCount,
            int flaggedCount,
            int genderCount) {

        if (genderCount == 0) {
            return "Diagnosis: No reference to women was detected.";
        }

        if (flaggedCount > 0) {
            return "Diagnosis: The sentence contains wording that may negatively stereotype women.";
        }

        if (appearanceCount > 0 && skillCount == 0) {
            return "Diagnosis: The sentence focuses on appearance without mentioning skill or intellect.";
        }

        if (skillCount > 0) {
            return "Diagnosis: The sentence includes positive skill, ability, or leadership language.";
        }

        if (emotionalCount > 0) {
            return "Diagnosis: The sentence contains emotional or nurturing language, but that alone does not prove bias.";
        }

        return "Diagnosis: No obvious bias was detected using the current word lists.";
    }
    public static void printSummary(String biasLevel, String diagnosis, String userText, String newText) {
        System.out.println();
        System.out.println("--- Summary ---");
        System.out.println("Bias level: " + biasLevel);
        System.out.println(diagnosis);

        if (!newText.equals(userText)) {
            System.out.println("Suggested rewrite: " + newText);
        } else {
            System.out.println("No rewrite changes needed based on current word list.");
        }
    }



}








