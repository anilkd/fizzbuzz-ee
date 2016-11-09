package com.ee.fizzbuzz.rules;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class FizzBuzzRuleServiceImplTest {

    @Test
    public void testGetRules() throws Exception {
        //given
        Predicate<Integer> isDivisibleByThree = i -> i % 3 == 0;

        FizzBuzzRuleService<FizBuzzRule> fizBuzzRuleFizzBuzzRuleService = new FizzBuzzRuleServiceImpl(Collections.singletonList(new FizBuzzRule(isDivisibleByThree, "Fizz")));
        //when
        List<FizBuzzRule> ruleList = fizBuzzRuleFizzBuzzRuleService.getRules();
        //then
        Assertions.assertThat(ruleList).hasSize(1).extracting("result").containsOnly("Fizz");
    }
}