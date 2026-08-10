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
            String analysisResult = analyzeSentence(userText);
            System.out.println(analysisResult);




        }
        scanner.close();


        System.out.println("Program ended.");
    }

    public static String analyzeSentence(String userText) {
        ArrayList<String> flaggedWords = createFlaggedWords();
        String[] userWords = userText.split(" ");

        System.out.println();
        System.out.println("--- Analysis Result ----");
        ArrayList<String> powerWords = createPowerwords();
        ArrayList<String> emotionalWords = createEmotionalWords();
        ArrayList<String> appearanceWords = createAppearancewords();
        ArrayList<String> genderWords = createGenderwords();
        ArrayList<String> emotionalPhrasesgen = createEmotionalphrases();
        ArrayList<String> leadershipPhrasesgen = leadershipPhrases();
        ArrayList<String> beautyPhrasesgen = createBeautyphrases();
        ArrayList<String> domesticPhrasesgen = createDomesticphrases();
        ArrayList<String> abilityPhrasesgen = abilityPhrases();
        ArrayList<String> generalizeStereotypes = createGeneralizationphrases();
        ArrayList<String> negationList = createNegation();
        ArrayList<String> negativeSkillWords = createNegativeSkillWords();




        int appearanceCount = checkWords(userWords, appearanceWords, "Appearance");
        int emotionalCount = checkWords(userWords, emotionalWords, "Emotional/support/nurturing");
        int skillCount = checkWords(userWords, powerWords, "Power/skill/brains");
        int genderCount = checkWords(userWords, genderWords, "Gender pronouns");
        int flaggedCount = checkWords(userWords, flaggedWords, "May be biased");
        int emotionalPhrasecount = checkPhrases(userText, emotionalPhrasesgen, "Emotional stereotype");
        int leadershipPhraseCount = checkPhrases(userText, leadershipPhrasesgen, "Leadership stereotype");
        int abilityPhrasecount = checkPhrases(userText, abilityPhrasesgen, "Ability stereotype");
        int beautyPhrasecount = checkPhrases(userText, beautyPhrasesgen, "Appearance ereotype");
        int domesticPhrasesCount= checkPhrases(userText, domesticPhrasesgen, "Domestic stereotype");
        int negativeSkillWordCount =checkWords(userWords, negativeSkillWords, "Negative skill");
        int generalizedPhrasecount = checkPhrases(userText, generalizeStereotypes, "Generalization stereotype");

         boolean isNegative =  dectectNegitiveskills(negationList,  powerWords, userText);

            boolean generalizationDetected = generalizedPhrasecount  > 0;

        System.out.println("-- BIAS AGAINST WOMEN COUNT ---");
        System.out.println("Appearance count: " + appearanceCount);
        System.out.println("Potentially biased word count: " + flaggedCount);
        System.out.println("Emotional/support count: " + emotionalCount);
        System.out.println("Skill and Power count: " + skillCount);
        System.out.println("Gender pronoun count: " + genderCount);
        System.out.println("Emotional stereotype against women phrase count: " + emotionalPhrasecount);
        System.out.println("Ability stereotype against women phrase count: " + abilityPhrasecount);
        System.out.println("Appearance stereotype against women phrase count: " + beautyPhrasecount);
        System.out.println("Leadership stereotype against women phrase count: " + leadershipPhraseCount);
        System.out.println("Domestic stereotype against women phrase count: " + domesticPhrasesCount);
        System.out.println("Generalized stereotype against women phrase count: " + generalizedPhrasecount);
        System.out.println("Generalized stereotype against women phrase count: " + generalizedPhrasecount);

        System.out.println("Negative skill meaning found: " + isNegative);


        int finalBiasCount = calculateBiasScore(
                appearanceCount,
                emotionalPhrasecount,
                beautyPhrasecount,
                abilityPhrasecount,
                leadershipPhraseCount,
                domesticPhrasesCount,
                genderCount,
                skillCount,
                flaggedCount,
                generalizedPhrasecount,
                negativeSkillWordCount,
                isNegative
        );

        System.out.println("Bias score: " + finalBiasCount);

        String biasLevel = getBiasLevel(finalBiasCount);

        String diagnosis = printDiagnosis(
                appearanceCount,
                emotionalCount,
                skillCount,
                flaggedCount,
                genderCount,
                isNegative,
                negativeSkillWordCount
        );

        System.out.println();


        String newText = rewriteWords(userText);

        StringBuilder results = new StringBuilder();

        //get final bias counts
        results.append("Bias level: ");
        results.append(biasLevel);
        results.append("\n");

        //get the diagnosis
        results.append(diagnosis);
        results.append("\n");

        if(!newText.equals(userText)) {
            results.append("Possible rewrite: ");
            results.append(newText);
        } else {
            results.append("No rewrite changes needed.");
        }

        return results.toString();
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

    public static int checkPhrases(String userWords, ArrayList<String> phraseList, String categoryName) {
        boolean foundAny = false;
        int count = 0;
        System.out.println(categoryName + " phrases found:");


        String cleanedText = userWords
                .toLowerCase()
                .replaceAll("[^a-z' ]", "");
        for (String phrase : phraseList) {
            if (cleanedText.contains(phrase)) {
                System.out.println("Found: " + phrase);
                count++;
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("No biased phrases found.");
        }

        return count;

    }
//she is not very capable
    public static boolean dectectNegitiveskills(ArrayList<String> negationList, ArrayList<String> powerList, String userText) {
        String[] userWords = userText.split(" ");
        int powerWordindex = -1;
        int negitiveWordindex = -1;

        for (int i = 0; i < userWords.length; i++) {
            String currentWord = userWords[i].replaceAll("[^a-z']", "");

            if (powerList.contains(currentWord)) {
                 powerWordindex = i;
            }
            for (int j = 0; j < i ; j++) {
                String prevWord = userWords[j].replaceAll("[^a-z']", "");
                if (negationList.contains(prevWord)) {
                    negitiveWordindex = j;
                }
                }
            if (powerWordindex != -1 && negitiveWordindex != -1) {
              int differenceVal = powerWordindex - negitiveWordindex;
                if (differenceVal > 0 && differenceVal <=3) {
                    return true;
                }
            }
        }
        return false;
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

    public static ArrayList<String> createGeneralizationphrases() {
        ArrayList<String> generalizeStereotypes = new ArrayList<String>();

        generalizeStereotypes.add("always");
        generalizeStereotypes.add("naturally");
        generalizeStereotypes.add("usually");
        generalizeStereotypes.add("all");
        generalizeStereotypes.add("women are");
        generalizeStereotypes.add("girls are");
        generalizeStereotypes.add("meant to");
        generalizeStereotypes.add("all women");
        generalizeStereotypes.add("all girls");
        generalizeStereotypes.add("every girl");
        generalizeStereotypes.add("every woman");
        generalizeStereotypes.add("never");
        generalizeStereotypes.add("women are born to");
        generalizeStereotypes.add("just how women are");
        generalizeStereotypes.add("girls are born to");
        generalizeStereotypes.add("women are biologically hardwired to");
        generalizeStereotypes.add("women are hardwired to");
        generalizeStereotypes.add("mothers naturally");
        generalizeStereotypes.add("typically");

        return generalizeStereotypes;
    }

    public static ArrayList<String> createNegation() {
        ArrayList<String> negationList = new ArrayList<String>();

        negationList.add("can't");
        negationList.add("incapable");
        negationList.add("couldn't");
        negationList.add("shouldn't");
        negationList.add("not");
        negationList.add("never");

        return negationList;
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

    public static ArrayList<String> createNegativeSkillWords() {
        ArrayList<String> negativeSkillWords = new ArrayList<>();

        negativeSkillWords.add("incapable");
        negativeSkillWords.add("incompetent");
        negativeSkillWords.add("unqualified");

        return negativeSkillWords;
    }

    public static ArrayList<String> createEmotionalphrases() {
        ArrayList<String> emotionalStereotypes = new ArrayList<String>();
        emotionalStereotypes.add("are emotional");
        emotionalStereotypes.add("are complicated");
        emotionalStereotypes.add("are always nagging");
        emotionalStereotypes.add("naturally emotional");
        emotionalStereotypes.add("aren't rowdy");
        emotionalStereotypes.add("are naturally weaker");
        emotionalStereotypes.add("too sensitive");
        emotionalStereotypes.add("are naturally irrational");
        emotionalStereotypes.add("always dramatic");


return emotionalStereotypes;
    }
    public static ArrayList<String> createDomesticphrases() {

        ArrayList<String> domesticSterotypes = new ArrayList<String>();
        domesticSterotypes.add("belong in the kitchen");
        domesticSterotypes.add("must cook and clean");
        domesticSterotypes.add("submit to their husband");
        domesticSterotypes.add("naturally want kids");
        domesticSterotypes.add("serves their husband");
        domesticSterotypes.add("make me a sandwich");
        domesticSterotypes.add("role  in the kitchen");
        domesticSterotypes.add("must take care of the children");
        domesticSterotypes.add("should stay at home");
        domesticSterotypes.add("should be a housewife");



        return domesticSterotypes;
    }

    public static ArrayList<String> createBeautyphrases() {

        ArrayList<String> beautySterotypes = new ArrayList<String>();
        beautySterotypes.add("only good for her looks");
        beautySterotypes.add("just a pretty face");
        beautySterotypes.add("too unattractive for");
        beautySterotypes.add("just chosen for her looks");
        beautySterotypes.add("all makeup");

        return beautySterotypes;
    }
    public static ArrayList<String> leadershipPhrases() {

        ArrayList<String> leadershipSterotypes = new ArrayList<String>();
        leadershipSterotypes.add("cannot lead");
        leadershipSterotypes.add("be followers");
        leadershipSterotypes.add("shouldn't be leaders");
        leadershipSterotypes.add("shouldn't be a leader");
        leadershipSterotypes.add("are not built to lead");
        leadershipSterotypes.add("too irrational to lead");
        leadershipSterotypes.add("not capable of leading");
        leadershipSterotypes.add("must be below man");
        leadershipSterotypes.add("too emotional to lead");
        leadershipSterotypes.add("women can't be a bosses");



        return leadershipSterotypes;
    }


    public static ArrayList<String> abilityPhrases() {

        ArrayList<String> abiltySterotypes = new ArrayList<String>();
        abiltySterotypes.add("can't be in stem");
        abiltySterotypes.add("can't beat a man");
        abiltySterotypes.add("can't play chess");
        abiltySterotypes.add("not smart enough");
        abiltySterotypes.add("aren't strong");
        abiltySterotypes.add("dumb blonde");
        abiltySterotypes.add("not simple");
        abiltySterotypes.add("not logical");
        abiltySterotypes.add("can't understand technology");


//hi there how r u
        return abiltySterotypes;
    }


        public static ArrayList<String> createPowerwords() {

        ArrayList<String> powerWords = new ArrayList<String>();
        powerWords.add("skilled");
        powerWords.add("technical");
        powerWords.add("smart");
        powerWords.add("leader");
        powerWords.add("leaders");
        powerWords.add("mathematicians");
        powerWords.add("intelligent");
        powerWords.add("thinker");
        powerWords.add("logical");
        powerWords.add("fighter");
        powerWords.add("fighters");
        powerWords.add("engineers");
        powerWords.add("developer");
        powerWords.add("programmer");
        powerWords.add("mathematics");
        powerWords.add("developers");
        powerWords.add("scientist");
        powerWords.add("scientists");
        powerWords.add("skilled");
        powerWords.add("lead");
        powerWords.add("code");
        powerWords.add("problem solver");
        powerWords.add("genius");
        powerWords.add("determined");
        powerWords.add("determination");
        powerWords.add("grit");
        powerWords.add("math");
        powerWords.add("technology");
        powerWords.add("confident");
        powerWords.add("boss");
        powerWords.add("assertive");
        powerWords.add("clever");
        powerWords.add("sharp");
        powerWords.add("powerful");
        powerWords.add("expert");
        powerWords.add("experts");
        powerWords.add("engineer");
        powerWords.add("mathematician");
        powerWords.add("capable");
        powerWords.add("ambitious");
        powerWords.add("driven");
        powerWords.add("coders");
        powerWords.add("programmers");
        powerWords.add("math");
        powerWords.add("physics");
        powerWords.add("engineers");
        powerWords.add("coding");

        return powerWords;
    }

    public static ArrayList<String> createEmotionalWords() {

        ArrayList<String> emotionalWords = new ArrayList<>();

        emotionalWords.add("kind");
        emotionalWords.add("helpful");
        emotionalWords.add("sensitive");
        emotionalWords.add("emotional");
        emotionalWords.add("caring");
        emotionalWords.add("sweet");
        emotionalWords.add("mature");
        emotionalWords.add("gentle");
        emotionalWords.add("loving");
        emotionalWords.add("nurturing");
        emotionalWords.add("bubbly");
        emotionalWords.add("perky");

        return emotionalWords;
    }
    public static ArrayList<String> createFlaggedWords() {

        ArrayList<String> flaggedWords = new ArrayList<>();

        flaggedWords.add("bossy");
        flaggedWords.add("chick");
        flaggedWords.add("submissive");
        flaggedWords.add("hysterical");
        flaggedWords.add("hysteric");
        flaggedWords.add("overemotional");
        flaggedWords.add("dramatic");
        flaggedWords.add("crazy");
        flaggedWords.add("irrational");
        flaggedWords.add("feisty");
        flaggedWords.add("sassy");
        flaggedWords.add("catty");
        flaggedWords.add("nag");
        flaggedWords.add("ditzy");
        flaggedWords.add("fake");


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
            int emotionalPhrasecount,
            int beautyPhrasecount,
            int abilityPhrasecount,
            int leadershipPhraseCount,
            int domesticPhrasecount,
            int genderCount,
            int skillCount,
            int flaggedCount,
            int generalizeCount,
            int negativeSkillWordCount,
            boolean isNegative
           ) {

        if (genderCount == 0) {
            return 0;
        }

        int finalBiasCount = flaggedCount * 2 + emotionalPhrasecount * 3 + beautyPhrasecount *3+ abilityPhrasecount *3 + domesticPhrasecount * 3+ leadershipPhraseCount * 3 + generalizeCount * 2 + negativeSkillWordCount * 3;


        if (appearanceCount > 0 && skillCount == 0) {
            finalBiasCount++;
        }

        if (isNegative) {
            finalBiasCount += 3;
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
            int genderCount,
            boolean isNeg,
            int negativeSkillWordCount) {

        if (genderCount == 0) {
            return "Diagnosis: No reference to women was detected.";
        }

        if (flaggedCount > 0) {
            return "Diagnosis: The sentence contains wording that may negatively stereotype women.";
        }

        if (appearanceCount > 0 && skillCount == 0) {
            return "Diagnosis: The sentence focuses on appearance without mentioning skill or intellect.";
        }

        if (isNegative || negativeSkillWordCount > 0) {
            return "Diagnosis: The sentence negatively describes a woman's skill or ability.";
        }

        if (skillCount > 0) {
            return "Diagnosis: The sentence includes positive skill, ability, or leadership language.";
        }

        if (emotionalCount > 0) {
            return "Diagnosis: The sentence contains emotional or nurturing language, but that alone does not prove bias.";
        }

        return "Diagnosis: No obvious bias was detected using the current word lists.";
    }




}









