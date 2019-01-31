package alonzo.tokenize;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

// TODO: Track line and character number.
public class Tokenizer {

    @AllArgsConstructor
    public enum Nommer {
        VARIABLE   ("[^"     // Ignore:
                        + "\\s"    //   - Whitespace
                        + "()"     //   - Parenthesis
                        + "λ\\\\"  //   - Lambda and \ characters
                        + "."      //   - Period
                        + "]"
                        + "+"      // one or more times
                , matcher -> new VariableToken(matcher.group())),
        CLOSE_PAREN("\\)", ign -> new CloseParen()),
        OPEN_PAREN ("\\(", ign -> new OpenParen()),
        DOT        ("\\.", ign -> new Dot()),
        LAMBDA     ("\\\\|λ", ign -> new Lambda());

        @NonNull private final Pattern pattern;
        @NonNull private final Function<Matcher, Token> tokenConstructor;

        Nommer(String regex, Function<Matcher, Token> tokenConstructor) {
            this(Pattern.compile(regex), tokenConstructor);
        }
    }

    public TokenizationResult tokenize(String inputExpr) {
        State state = State.builder().remainingInputExpr(inputExpr).build();

        while(state.hasRemainingInput()) {
            state = nom(state);
        }

        return TokenizationResult.builder().tokens(state.getTokens()).build();
    }

    private State nom(State inputState) throws UnableToNomException {
        final String remainingInput = inputState.getRemainingInputExpr().stripLeading();

        for (Nommer nommer: Nommer.values()) {
            Matcher matcher = nommer.pattern.matcher(remainingInput);
            if (matcher.lookingAt()) {
                return inputState.toBuilder()
                        .remainingInputExpr(remainingInput.substring(matcher.end()))
                        .token(nommer.tokenConstructor.apply(matcher))
                        .build();
            }
        }

        // TODO: Put information in this exception (related to invalid token test)
        throw new UnableToNomException(inputState.getRemainingInputExpr());
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
