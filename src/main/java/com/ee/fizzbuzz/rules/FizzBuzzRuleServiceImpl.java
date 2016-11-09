package com.ee.fizzbuzz.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FizzBuzzRuleServiceImpl implements FizzBuzzRuleService<FizBuzzRule> {

    //rule predicates
    private static final Predicate<Integer> isDivisibleByThree = i -> i % 3 == 0;
    private static final Predicate<Integer> isNumberContainsThree = i -> String.valueOf(i).contains("3");
    private static final Predicate<Integer> isDivisibleByThreeAndFive = i -> i % 3 == 0 && i % 5 == 0;
    private static final Predicate<Integer> isDivisibleByFive = i -> i % 5 == 0;

    private List<FizBuzzRule> rules = new ArrayList<>();

    public FizzBuzzRuleServiceImpl() {
        addRule(isNumberContainsThree, "lucky");
        addRule(isDivisibleByThreeAndFive, "fizzbuzz");
        addRule(isDivisibleByThree, "fizz");
        addRule(isDivisibleByFive, "buzz");
    }

  /*  public FizzBuzzRuleServiceImpl(List<FizBuzzRule> fizBuzzRules) {
        rules.addAll(fizBuzzRules);
    }*/

    @Override
    public List<FizBuzzRule> getRules() {
        return rules;
    }

    private void addRule(Predicate<Integer> predicate, String result) {
        rules.add(new FizBuzzRule(predicate, result));
    }

}
