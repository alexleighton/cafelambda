package alonzo.tokenize;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TokenizerTest {

    @Parameterized.Parameters
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
            { "", emptyList() },
            { " \t\n\r", emptyList() },
            { "varName", List.of(new VariableToken("varName")) },
            { "varName!?_my123,/", List.of(new VariableToken("varName!?_my123,/")) },
            { "varName)", List.of(new VariableToken("varName"), new CloseParen()) }
        });
    }

    private String input;
    private TokenizationResult expectedResult;

    public TokenizerTest(String input, List<Token> expectedTokens) {
        this.input = input;
        this.expectedResult = TokenizationResult.builder().tokens(expectedTokens).build();
    }

    @Test
    public void tokens() {
        TokenizationResult result = new Tokenizer().tokenize(input);
        assertThat(result, equalTo(expectedResult));
    }

}