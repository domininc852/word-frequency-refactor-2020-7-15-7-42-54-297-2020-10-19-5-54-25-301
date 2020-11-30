import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        if (sentence.split(WHITE_SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(WHITE_SPACE_REGEX);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    wordFrequencyList.add(new WordFrequency(word, 1));
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordCountMap = getWordCuntMap(wordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                    wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
                }
                wordFrequencyList = wordCountList;
                wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());
                StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE);
                for (WordFrequency word : wordFrequencyList) {
                    String s = word.getWord() + " " + word.getCount();
                    wordFrequencyResult.add(s);
                }
                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordCuntMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency word : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordCountMap.containsKey(word.getWord())) {
                ArrayList wordList = new ArrayList<>();
                wordList.add(word);
                wordCountMap.put(word.getWord(), wordList);
            } else {
                wordCountMap.get(word.getWord()).add(word);
            }
        }
        return wordCountMap;
    }
}
