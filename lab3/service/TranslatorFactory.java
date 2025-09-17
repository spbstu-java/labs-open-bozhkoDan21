package service;

import exception.FileReadException;
import exception.InvalidFileFormatException;

public class TranslatorFactory {
    public static DictionaryService createEnglishRussianTranslator(String dictionaryPath)
            throws FileReadException, InvalidFileFormatException {
        EnglishRussianTranslator translator = new EnglishRussianTranslator();
        translator.loadDictionary(dictionaryPath);
        return translator;
    }
}