import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordCountList = buildWordCountList(sentence);
            wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());
            StringJoiner wordFrequencyResult = generateWordFrequency(wordCountList);
            return wordFrequencyResult.toString();
        } catch (Exception exception) {
            return "Calculate Error";
        }

    }

    private StringJoiner generateWordFrequency(List<WordFrequency> wordCountList) {
        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE);
        for (WordFrequency word : wordCountList) {
            String wordFrequencyLine = String.format("%s %d",word.getWord(),word.getCount());
            wordFrequencyResult.add(wordFrequencyLine);
        }
        return wordFrequencyResult;
    }

    private List<WordFrequency> buildWordCountList(String sentence) {
        return Arrays.asList(sentence.split(WHITE_SPACE_REGEX))
                .stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(Arrays.asList(sentence.split(WHITE_SPACE_REGEX)), word)))
                .collect(Collectors.toList());
    }
}
