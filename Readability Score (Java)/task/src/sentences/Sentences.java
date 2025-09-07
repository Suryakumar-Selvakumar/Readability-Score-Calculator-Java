package sentences;

import java.util.ArrayList;

public class Sentences {
    private final ArrayList<String> sentences;
    private int totalWordCount;
    private final int totalCharCount;
    private int syllablesCount;
    private int polysyllablesCount;

    public Sentences(ArrayList<String> sentencesArr, int totalCharCount) {
        this.sentences = new ArrayList<>();
        populateSentences(sentencesArr);
        this.totalCharCount = totalCharCount;
    }

    public void populateSentences(ArrayList<String> sentencesArr) {
        for (String sentence : sentencesArr) {
            String[] words = sentence.split(" ");
            for (String word : words) {
                syllablesCount += getSyllablesFromWord(word);
            }
            int wordCount = words.length;
            sentences.add(sentence);
            totalWordCount += wordCount;
        }
    }


    private int getSyllablesFromWord(String word) {
        int syllableCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                ++syllableCount;
            }

            if (i + 1 < word.length() && isVowel(word.charAt(i)) && isVowel(word.charAt(i + 1))) {
                --syllableCount;
            }

            if (i == word.length() - 1 && word.charAt(i) == 'e') {
                --syllableCount;
            }
        }

        if (syllableCount > 2) {
            ++polysyllablesCount;
        }

        return syllableCount == 0 ? 1 : syllableCount;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' ||
                ch == 'e' || ch == 'E' ||
                ch == 'i' || ch == 'I' ||
                ch == 'o' || ch == 'O' ||
                ch == 'u' || ch == 'U' ||
                ch == 'y' || ch == 'Y';
    }

    private double computeARIScore() {
        return 4.71 * ((double) totalCharCount / totalWordCount) +
                0.5 * ((double) totalWordCount / sentences.size()) -
                21.43;
    }

    private double computeFKScore() {
        return 0.39 * ((double) totalWordCount / sentences.size()) +
                11.8 * ((double) syllablesCount / totalWordCount) -
                15.59;
    }

    private double computeSMOGScore() {
        return 1.043 * Math.sqrt((double) polysyllablesCount * 30 / sentences.size()) +
                3.1291;
    }

    private double computeCLScore() {
        double L = ((double) totalCharCount / totalWordCount) * 100;
        double S = ((double) sentences.size() / totalWordCount) * 100;

        return (0.0588 * L) - (0.296 * S) - 15.8;
    }

    private static int getAgeRange(double score) {
        return switch ((int) Math.ceil(score)) {
            case 1 -> 6;
            case 2 -> 7;
            case 3 -> 8;
            case 4 -> 9;
            case 5 -> 10;
            case 6 -> 11;
            case 7 -> 12;
            case 8 -> 13;
            case 9 -> 14;
            case 10 -> 15;
            case 11 -> 16;
            case 12 -> 17;
            case 13 -> 18;
            default -> 22;
        };
    }

    public void printStats() {
        System.out.printf("""
                        Words: %d
                        Sentences: %d
                        Characters: %d
                        Syllables: %d
                        Polysyllables: %d
                        Enter the score you want to calculate (ARI, FK, SMOG, CL, all):""",
                totalWordCount, sentences.size(), totalCharCount, syllablesCount, polysyllablesCount);
    }

    public void printScores(String scoreChoice) {
        double ARI, FK, SMOG, CL;
        int ageARI, ageFK, ageSMOG, ageCL;

        switch (scoreChoice) {
            case "ARI":
                ARI = computeARIScore();
                ageARI = getAgeRange(ARI);
                System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).", ARI, ageARI);
                break;
            case "FK":
                FK = computeFKScore();
                ageFK = getAgeRange(FK);
                System.out.printf("Flesch-Kincaid readability tests: %.2f (about %d-year-olds).", FK, ageFK);
                break;
            case "SMOG":
                SMOG = computeSMOGScore();
                ageSMOG = getAgeRange(SMOG);
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).", SMOG, ageSMOG);
                break;
            case "CL":
                CL = computeCLScore();
                ageCL = getAgeRange(CL);
                System.out.printf("Coleman-Liau index: %.2f (about %d-year-olds).", CL, ageCL);
                break;
            case "all":
                ARI = computeARIScore();
                ageARI = getAgeRange(ARI);
                FK = computeFKScore();
                ageFK = getAgeRange(FK);
                SMOG = computeSMOGScore();
                ageSMOG = getAgeRange(SMOG);
                CL = computeCLScore();
                ageCL = getAgeRange(CL);
                double averageAge = (ageARI + ageFK + ageSMOG + ageCL) / 4.0;
                System.out.printf("""
                        automated Readability Index: %.2f (about %d-year-olds).
                        Flesch-Kincaid readability tests: %.2f (about %d-year-olds).
                        Simple Measure of Gobbledygook: %.2f (about %d-year-olds).
                        Coleman-Liau index: %.2f (about %d-year-olds).
                        
                        This text should be understood in average by %.2f-year-olds.
                        """, ARI, ageARI, FK, ageFK, SMOG, ageSMOG, CL, ageCL, averageAge);
                break;
        }
    }
}
