package alonzo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Main {

    @Getter @NonNull private final String message;

    public static void main(String[] args) {
        Main main = new Main("success");
        System.out.println(main.getMessage());
    }

}
