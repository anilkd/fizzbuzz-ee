package com.ee.fizzbuzz.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class FizBuzzRule<T, R> {
    private Predicate<T> predicate;
    private R result;
}
