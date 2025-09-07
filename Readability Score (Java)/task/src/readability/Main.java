package readability;

import sentences.Sentences;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder text;
        try {
            text = new StringBuilder(readFileText(args[0]).replaceAll("\\. ", "."));
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
            return;
        }

        ArrayList<String> strSentences = new ArrayList<>();

        int totalCharCount = 1;
        int start = 0;

        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i - 1) == '!' || text.charAt(i - 1) == '.' || text.charAt(i - 1) == '?') {
                strSentences.add(text.substring(start, i == text.length() - 1 ? i + 1 : i));
                start = i == text.length() - 1 ? i + 1 : i;
            }
            if (text.charAt(i) != ' ' && text.charAt(i) != '\n' && text.charAt(i) != '\t') {
                totalCharCount++;
            }
        }
        if (start != text.length()) {
            text.delete(0, start);
            strSentences.add(text.toString());
        }

        Sentences sentences = new Sentences(strSentences, totalCharCount);
        sentences.printStats();

        String scoreChoice = sc.nextLine().trim();
        System.out.println();
        sentences.printScores(scoreChoice);
    }

    public static String readFileText(String pathName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathName)));
    }
}
