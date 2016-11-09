package com.ee.fizzbuzz.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class FizBuzzRule {

    private Predicate<Integer> predicate;
    private String result;
}
