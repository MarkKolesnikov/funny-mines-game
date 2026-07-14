package secret_word.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileWordSource implements WordSource {

    private final ResourceLoader resourceLoader;

    public FileWordSource(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<String> getWords() {
        try {
            Resource resource = resourceLoader.getResource("src/main/resources/word_mines.txt");
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            return Arrays.stream(content.split(","))
                    .map(String::trim)
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new IllegalArgumentException("Не удалось прочитать файл со словами!", ex);
        }
    }
}
