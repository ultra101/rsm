package org.example.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooBarTest {

    static Stream<Arguments> repeatingFoos() {
        return Stream.of(
                Arguments.of("3,3,3", "foo,foo-copy,foo-copy"),
                Arguments.of("-3,3,3", "foo,foo,foo-copy"),
                Arguments.of("-3,-3,-3", "foo,foo-copy,foo-copy"),
                Arguments.of("33,3,3,33", "foo,foo,foo-copy,foo-copy")
        );
    }

    static Stream<Arguments> repeatingBars() {
        return Stream.of(
                Arguments.of("5,5,5", "bar,bar-copy,bar-copy"),
                Arguments.of("-5,5,5", "bar,bar,bar-copy"),
                Arguments.of("-5,-5,-5", "bar,bar-copy,bar-copy"),
                Arguments.of("55,5,5,55", "bar,bar,bar-copy,bar-copy")
        );
    }

    static Stream<Arguments> repeatingFooBars() {
        return Stream.of(
                Arguments.of("15,15,15", "foobar,foobar-copy,foobar-copy"),
                Arguments.of("-15,15,15", "foobar,foobar,foobar-copy"),
                Arguments.of("-15,-15,-15", "foobar,foobar-copy,foobar-copy"),
                Arguments.of("75,105,105,75", "foobar,foobar,foobar-copy,foobar-copy")
        );
    }

    static Stream<Arguments> repeatingNumbers() {
        return Stream.of(
                Arguments.of("0,0,0", "0,0-copy,0-copy"),
                Arguments.of("-1,1,1", "-1,1,1-copy"),
                Arguments.of("-1,-1,1", "-1,-1-copy,1"),
                Arguments.of("11,1,1,11", "11,1,1-copy,11-copy")
        );
    }

    @Test
    public void nonRepeatingNumbersAndNoDivisibleByThreeAndFiveTest() {
        String input = "1,2,-908,101,9997";
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(input, result);
    }

    @Test
    public void zeroValueIsHandledProperlyTest() {
        String input = "0";
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(input, result);
    }

    @ParameterizedTest
    @EmptySource
    public void shouldReturnTrueForEmptyStringInputTest(String input) {
        FooBar fooBar = new FooBar();

        assertTrue(fooBar.fooBar(input).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "96", "-216"})
    public void fooLogicIsAppliedTest(String input) {
        FooBar fooBar = new FooBar();
        String expected = "foo";
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "100", "-5"})
    public void barLogicIsAppliedTest(String input) {
        FooBar fooBar = new FooBar();
        String expected = "bar";
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"15", "105", "-45"})
    public void foobarLogicIsAppliedTest(String input) {
        FooBar fooBar = new FooBar();
        String expected = "foobar";
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("repeatingFoos")
    public void repeatingValuesDivisibleByThreeTest(String input, String expected) {
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("repeatingBars")
    public void repeatingValuesDivisibleByFiveTest(String input, String expected) {
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("repeatingFooBars")
    public void repeatingValuesDivisibleByThreeAndFiveTest(String input, String expected) {
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("repeatingNumbers")
    public void repeatingNumbersTest(String input, String expected) {
        FooBar fooBar = new FooBar();
        String result = String.join(",", fooBar.fooBar(input));

        assertEquals(expected, result);
    }
}
