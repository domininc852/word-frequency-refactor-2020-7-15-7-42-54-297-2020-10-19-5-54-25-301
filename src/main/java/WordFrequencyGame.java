import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        List<WordFrequency> wordCountList = buildWordCountList(sentence);
        wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());
        return generateWordFrequency(wordCountList);
    }

    private String generateWordFrequency(List<WordFrequency> wordCountList) {
        return wordCountList.stream().map(word -> String.format("%s %d", word.getWord(), word.getCount())).collect(Collectors.joining(NEW_LINE));
    }

    private List<WordFrequency> buildWordCountList(String sentence) {
        return Arrays.asList(sentence.split(WHITE_SPACE_REGEX))
                .stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(Arrays.asList(sentence.split(WHITE_SPACE_REGEX)), word)))
                .collect(Collectors.toList());
    }
}
