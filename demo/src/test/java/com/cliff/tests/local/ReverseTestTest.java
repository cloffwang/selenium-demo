package com.cliff.tests.local;

import com.cliff.ReverseTest;
/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;*/

import java.util.stream.Stream;

public class ReverseTestTest {
    private ReverseTest reverseTest;

    /*static Stream<Arguments> testDataInNums() {
        return Stream.of(
                Arguments.of(1234,4321),
                Arguments.of(0, 0),
                Arguments.of(1111, 1111)
        );
    }*/

    /*static Stream<Arguments> testDataInString() {
        return Stream.of(
                Arguments.of("abcd","dcba"),
                Arguments.of("a", "a"),
                Arguments.of("bbbb", "bbbb")
        );
    }*/

    //@BeforeEach
    public void setUp() {
        this.reverseTest = new ReverseTest();
    }

    //@ParameterizedTest
    //@MethodSource("testDataInNums")
    public void testReverseNum(int input, int output) {
    //    Assertions.assertEquals(reverseTest.reverse(input), output);
    }

    //@ParameterizedTest
    //@MethodSource("testDataInString")
    public void testReverseString(String input, String output) {
    //    Assertions.assertEquals(reverseTest.reverse(input), output);
    }
}
