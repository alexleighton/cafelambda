package alonzo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
public class ProgramOutput {
    @NonNull private final String expression;
}
