package com.ee.fizzbuzz.rules;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Factory to get list of fizz buzz predicates
 */
public class FizzBuzzPredicateFactory {

    //rule predicates
    private static Predicate<Integer> isDivisibleByThree = i -> i % 3 == 0;
    private static Predicate<Integer> isDivisibleByThreeAndFive = i -> i % 3 == 0 && i % 5 == 0;
    private static Predicate<Integer> isDivisibleByFive = i -> i % 5 == 0;

    private FizzBuzzPredicateFactory(){

    }


    public static Map<Predicate<Integer>, String> getPredicateMap() {
        Map<Predicate<Integer>, String> predicateMap = new LinkedHashMap<>();
        predicateMap.put(isDivisibleByThreeAndFive, "fizzbuzz");
        predicateMap.put(isDivisibleByThree, "fizz");
        predicateMap.put(isDivisibleByFive, "buzz");
        return predicateMap;
    }
}
