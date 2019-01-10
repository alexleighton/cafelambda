package alonzo.tokenize;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.commons.lang3.StringUtils;

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

    private static final Pattern CLOSE_PAREN_PATTERN = Pattern.compile("\\)");

    public TokenizationResult tokenize(String inputExpr) {
        State state = State.builder().remainingInputExpr(inputExpr).build();
        TokenizationResult.TokenizationResultBuilder tokenizationResultBuilder = TokenizationResult.builder();


        // TODO: Iterate nom, add (-nom
        while(state.hasRemainingInput())
        {
            state = nom(state);
        }


        return TokenizationResult.builder().tokens(state.getTokens()).build();
    }

    private State nom(State inputState) throws UnableToNomException {
        // TODO: Factor out this builder duplicated logic
        // TODO: Add test around invalid tokens (do we need? -- Steven was making a point that this might be impossible)
        // TODO: Do subnoms; nomVar, nomParen (or do a list of patterns and try them all, but Steven don't like it)

        Matcher matcher = VAR_PATTERN.matcher(inputState.getRemainingInputExpr());
        if (matcher.lookingAt()) {
             return  inputState.toBuilder().remainingInputExpr(inputState.getRemainingInputExpr()
                     .substring(matcher.end()))
                     .token(new VariableToken(matcher.group()))
                     .build();
        }

        Matcher closeParenMatcher = CLOSE_PAREN_PATTERN.matcher(inputState.getRemainingInputExpr());
        if (closeParenMatcher.lookingAt()) {
            return inputState.toBuilder().remainingInputExpr(inputState.getRemainingInputExpr()
                    .substring(closeParenMatcher.end()))
                    .token(new CloseParen())
                    .build();
        }

        // TODO: Put information in this exception (related to invalid token test)
        throw new UnableToNomException();
    }

    @AllArgsConstructor
    @Getter
    @Builder(toBuilder = true, builderClassName = "Builder")
    public static class State {
        @Singular private final List<Token> tokens;
        private final String remainingInputExpr;

        public boolean hasRemainingInput() {
            return !remainingInputExpr.isBlank();
        }
    }
}
