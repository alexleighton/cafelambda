package alonzo;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ProgramInput {
    @NonNull private final String expression;

    public String expression() {
        return expression;
    }
}
