package service;

import exception.FileReadException;
import exception.InvalidFileFormatException;

public interface DictionaryService {
    void loadDictionary(String filePath) throws FileReadException, InvalidFileFormatException;
    String translate(String text);
    boolean containsWord(String word);
}