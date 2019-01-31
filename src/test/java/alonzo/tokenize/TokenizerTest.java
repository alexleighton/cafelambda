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

    // TODO: Add test around invalid tokens (do we need? -- Steven was making a point that this might be impossible)
    @Parameterized.Parameters
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
            { "", emptyList() },
            { " \t\n\r", emptyList() },
            { "varName", List.of(new VariableToken("varName")) },
            { "varName!?_my123,/", List.of(new VariableToken("varName!?_my123,/")) },
            { "varName)", List.of(new VariableToken("varName"), new CloseParen()) },
            { "(", List.of(new OpenParen()) },
            { ".", List.of(new Dot()) },
            { "\\", List.of(new Lambda()) },
            { "λ", List.of(new Lambda()) },
            { "(λage. (isMillenial age)) forty)", List.of(
                    new OpenParen(),
                        new Lambda(), new VariableToken("age"), new Dot(),
                            new OpenParen(), new VariableToken("isMillenial"), new VariableToken("age"), new CloseParen(),
                        new CloseParen(),
                        new VariableToken("forty"),
                    new CloseParen())
            },
            { " .", List.of(new Dot()) }
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