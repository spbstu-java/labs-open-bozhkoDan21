package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import exception.InvalidFileFormatException;
import exception.FileReadException;

public class EnglishRussianTranslator implements DictionaryService {
    private final Map<String, String> dictionary = new HashMap<>();
    private int maxPhraseLength;
    private boolean dictionaryLoaded = false;

    @Override
    public void loadDictionary(String filePath) throws FileReadException, InvalidFileFormatException {
        dictionary.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Отсутствует разделитель '|' в строке " + lineNumber);
                }

                String english = parts[0].trim().toLowerCase();
                String russian = parts[1].trim();

                if (english.isEmpty()) {
                    throw new InvalidFileFormatException("Пустое английское слово в строке " + lineNumber);
                }

                this.dictionary.put(english, russian);
            }

            if (dictionary.isEmpty()) {
                throw new InvalidFileFormatException("Словарь пуст");
            }

            this.maxPhraseLength = calculateMaxPhraseLength();
            this.dictionaryLoaded = true;

        } catch (IOException e) {
            throw new FileReadException(filePath, e);
        }
    }

    @Override
    public String translate(String text) {
        if (!dictionaryLoaded) {
            throw new IllegalStateException("Словарь не загружен");
        }

        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String[] sentences = text.toLowerCase().split("(?<=[.!?])\\s*");
        StringBuilder translatedText = new StringBuilder();

        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (!sentence.isEmpty()) {
                String translatedSentence = translateSentence(sentence);
                if (!translatedSentence.isEmpty()) {
                    translatedText.append(translatedSentence).append(" ");
                }
            }
        }

        return translatedText.toString().trim();
    }

    @Override
    public boolean containsWord(String word) {
        return dictionary.containsKey(word.toLowerCase());
    }

    private int calculateMaxPhraseLength() {
        int maxLength = 0;
        for (String key : dictionary.keySet()) {
            int wordCount = key.split(" ").length;
            if (wordCount > maxLength) {
                maxLength = wordCount;
            }
        }
        return maxLength;
    }

    private String translateSentence(String sentence) {
        String cleanSentence = sentence.replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", " ");
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(cleanSentence.split("\\s+")));
        wordsList.removeIf(String::isEmpty);

        int i = 0;
        while (i < wordsList.size()) {
            boolean translationFound = false;

            for (int length = Math.min(maxPhraseLength, wordsList.size() - i); length > 0; length--) {
                String currentPhrase = String.join(" ", wordsList.subList(i, i + length));

                if (dictionary.containsKey(currentPhrase)) {
                    String translation = dictionary.get(currentPhrase);
                    if (!translation.isEmpty()) {
                        wordsList.set(i, translation);
                    } else {
                        wordsList.set(i, "");
                    }

                    for (int j = 1; j < length; j++) {
                        wordsList.remove(i + 1);
                    }

                    translationFound = true;
                    break;
                }
            }

            if (!translationFound) {
                i++;
            }
        }

        // Удаляем пустые строки (слова без перевода)
        wordsList.removeIf(String::isEmpty);

        if (wordsList.isEmpty()) {
            return "";
        }

        // Восстанавливаем регистр и пунктуацию
        return restoreFormatting(wordsList, sentence);
    }

    private String restoreFormatting(ArrayList<String> words, String originalSentence) {
        // Первая буква заглавная
        if (!words.isEmpty()) {
            String firstWord = words.get(0);
            if (!firstWord.isEmpty()) {
                words.set(0, capitalizeFirstLetter(firstWord));
            }
        }

        // Знаки препинания в конце
        if (!words.isEmpty()) {
            String lastWord = words.get(words.size() - 1);
            char lastChar = originalSentence.charAt(originalSentence.length() - 1);
            if (!Character.isLetterOrDigit(lastChar) && !Character.isWhitespace(lastChar)) {
                words.set(words.size() - 1, lastWord + lastChar);
            }
        }

        return String.join(" ", words);
    }

    private String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}