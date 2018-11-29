package alonzo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {

    private Main main = new Main("success");

    @Test
    public void success() {
        assertThat(main.getMessage(), equalTo("foo"));
    }

}
