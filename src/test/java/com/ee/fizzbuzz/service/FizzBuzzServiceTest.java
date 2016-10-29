package com.ee.fizzbuzz.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FizzBuzzServiceTest {

    private FizzBuzzService fizzBuzzService = new FizzBuzzServiceImpl();


    @Test
    public void shouldReturnFizzWhenInputIsThree() {
        //given
        int start = 3, end = 3;
        //when
        String result = fizzBuzzService.parseNumbers(start, end);
        //then
        assertThat(result).isEqualTo("lucky");
    }

    @Test
    public void shouldReturnBuzzWhenInputIsFive() {
        //given
        int start = 5, end = 5;
        //when
        String result = fizzBuzzService.parseNumbers(start, end);
        //then
        assertThat(result).isEqualTo("buzz");
    }

    @Test
    public void shouldSuccessfullyParseInputWithRange1to20() {
        //given
        int start = 1, end = 20;
        String expectedResult = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        //when
        String result = fizzBuzzService.parseNumbers(start, end);
        //then
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRangeIsNegative() {
        //given
        int start = -5, end = 5;
        //when
        Throwable throwable = catchThrowable(() -> fizzBuzzService.parseNumbers(start, end));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid input with range start:-5 end:5");
    }

    @Test
    public void shouldThrowExceptionWhenRangeIsInvalid() {
        //given
        int start = 5, end = 3;
        //when
        Throwable throwable = catchThrowable(() -> fizzBuzzService.parseNumbers(start, end));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid input with range start:5 end:3");
    }

    @Test
    public void shouldReturnLuckyWhenNumberContainsThree() {
        //given
        int start = 13, end = 13;
        String expectedResult = "lucky";
        //when
        String result = fizzBuzzService.parseNumbers(start, end);
        //then
        assertThat(result).isEqualTo(expectedResult);
    }
}
