package alonzo.tokenize;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private static final Pattern VAR_PATTERN = Pattern.compile(
            "[^"     // Ignore:
          + "\\s"    //   - Whitespace
          + "()"     //   - Parenthesis
          + "λ\\\\"  //   - Lambda and \ characters
          + "."      //   - Period
          + "]"
          + "+"      // one or more times
    );

    public TokenizationResult tokenize(String inputExpr) {
        Token token = null;

        // TODO: Iterate nom, add (-nom
        token = nom(inputExpr);

        return TokenizationResult.builder()
                .tokens(token == null ? Collections.emptyList() : Collections.singletonList(token))
                .build();
    }

    private Token nom(String inputExpr) {
        Matcher matcher = VAR_PATTERN.matcher(inputExpr);
        if (matcher.lookingAt()) {
             return new VariableToken(matcher.group());
        }
        return null;
    }

}
