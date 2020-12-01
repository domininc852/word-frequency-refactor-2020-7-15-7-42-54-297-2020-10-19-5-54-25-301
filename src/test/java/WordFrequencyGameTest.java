import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class WordFrequencyGameTest {

    @Test
    void should_get_the_1_when_input_the() throws CalculateErrorException {
        //Given
        String inputStr = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_process_two_words() throws CalculateErrorException {
        //Given
        String inputStr = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_process_two_words_with_special_spaces() throws CalculateErrorException {
        //Given
        String inputStr = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_process_two_words_with_special_enter() throws CalculateErrorException {
        //Given
        String inputStr = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_pracess_two_same_words_with_sorted() throws CalculateErrorException {
        //Given
        String inputStr = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_process_sorted_with_count_descending() throws CalculateErrorException {
        //Given
        String inputStr = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    void should_return_calculate_error_message() {
        //Given
        String inputStr = null;
        WordFrequencyGame game = new WordFrequencyGame();
        final CalculateErrorException calculateErrorException = assertThrows(CalculateErrorException.class, () -> {
            game.getResult(inputStr);
        });
        assertEquals("Calculate Error", calculateErrorException.getMessage());
    }

    private void validate_Input_words_process_to_expected_word(String inputStr, String expectResult) throws CalculateErrorException {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(inputStr);
        //Then
        assertEquals(result, expectResult);
    }
}
