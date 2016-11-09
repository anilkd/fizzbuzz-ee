package com.ee.fizzbuzz.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FizzBuzzRuleServiceImpl implements FizzBuzzRuleService<FizBuzzRule<Integer, String>> {
    //rule predicates
    private static final Predicate<Integer> isDivisibleByThree = i -> i % 3 == 0;
    private static final Predicate<Integer> isNumberContainsThree = i -> String.valueOf(i).contains("3");
    private static final Predicate<Integer> isDivisibleByThreeAndFive = i -> i % 3 == 0 && i % 5 == 0;
    private static final Predicate<Integer> isDivisibleByFive = i -> i % 5 == 0;

    private List<FizBuzzRule<Integer, String>> rules = new ArrayList<>();

    public FizzBuzzRuleServiceImpl() {
        addRule(isNumberContainsThree, "lucky");
        addRule(isDivisibleByThreeAndFive, "fizzbuzz");
        addRule(isDivisibleByThree, "fizz");
        addRule(isDivisibleByFive, "buzz");
    }


    @Override
    public List<FizBuzzRule<Integer, String>> getRules() {
        return rules;
    }

    private void addRule(Predicate<Integer> predicate, String result) {
        rules.add(new FizBuzzRule<Integer, String>(predicate, result));
    }

}
