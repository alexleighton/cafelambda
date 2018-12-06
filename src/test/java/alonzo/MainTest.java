package alonzo;

import alonzo.exceptions.InvalidLambdaExpression;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {

    private Main main = new Main("success");

    @Test
    public void interpret() {
        ProgramInput input = new ProgramInput("(λf. (λx. x))");
        assertThat(main.evaluate(input), equalTo(new ProgramOutput("(λf. (λx. x))")));
    }

    @Test(expected = InvalidLambdaExpression.class)
    public void emptyInput() {
        ProgramInput input = new ProgramInput("");
        main.evaluate(input);
    }
}
