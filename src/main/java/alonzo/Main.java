package alonzo;

import alonzo.exceptions.InvalidLambdaExpression;
import alonzo.model.LambdaExpression;
import alonzo.tokenize.Tokenizer;
import alonzo.tokenize.TokenizationResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class Main {

    @Getter @NonNull private final String message;

    public static void main(String[] args) {
        Main main = new Main("success");
        System.out.println(main.getMessage());
    }

    public ProgramOutput evaluate(ProgramInput input) {
        if(StringUtils.isBlank(input.expression())) {
            throw new InvalidLambdaExpression();
        }

        //E.g. ((λf.f) y)
        //E.g. ((λy.(λx.x)) y)
        //((λV.E) E′)  is E[V := E′]
        LambdaExpression expression = transform(input);
        if(expression.isFunctionApplication()) {
            return applyFunction(expression);
        }

        return new ProgramOutput(input.expression());
    }

    private ProgramOutput applyFunction(LambdaExpression expression) {
        return null;
    }

    private LambdaExpression transform(ProgramInput input) {
        String inputExpr = input.expression();

        /**
         * x 	Variable 	A character or string representing a parameter or mathematical/logical value
         * (λx.M) 	Abstraction 	Function definition (M is a lambda term). The variable x becomes bound in the expression.
         * (M N) 	Application 	Applying a function to an argument. M and N are lambda terms.
         */
        TokenizationResult tokenized = new Tokenizer().tokenize(inputExpr);
//        return parse(tokenized);
        return null;
    }

}
