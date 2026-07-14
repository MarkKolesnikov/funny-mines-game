package secret_word.service;

import exceptions.NoWordsLeftException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WordService {

    private final List<String> shuffledWords;
    private int currentIndex = 0;

    public WordService(WordSource wordSource) {
        List<String> allWords = wordSource.getWords();
        if (allWords.isEmpty()) {
            throw new IllegalStateException("В файле нет ни одного валидного слова");
        }
        Collections.shuffle(allWords); // один раз тасуем колоду
        this.shuffledWords = Collections.unmodifiableList(allWords);
        this.currentIndex = 0; // начинаем с первого слова
    }

    public String generateWord() {
        if (currentIndex >= shuffledWords.size()) {
            throw new NoWordsLeftException();
        }
        return shuffledWords.get(currentIndex++);
    }
}
