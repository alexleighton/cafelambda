package alonzo.ast;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class AstVariableNodeTest {

    @Test
    public void hasSymbol() {
        AstVariableNode node = new AstVariableNode("x");
        assertThat(node.getSymbol(), equalTo("x"));
    }

    @Test(expected = AstArgumentException.class)
    public void symbolIsDigits() {
        new AstVariableNode("0");
    }
}
