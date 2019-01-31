package alonzo.tokenize;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UnableToNomException extends RuntimeException {

    private final String remainingInput;

    public UnableToNomException(@NonNull String remainingInput) {
        super("Unable to Nom, remaining input: ~\"" + remainingInput + "\"~");
        this.remainingInput = remainingInput;
    }

}
