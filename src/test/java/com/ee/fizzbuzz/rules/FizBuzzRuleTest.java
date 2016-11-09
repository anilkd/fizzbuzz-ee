package com.ee.fizzbuzz.rules;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FizBuzzRuleTest {

    @Test
    public void shouldSuccessfullyCreateRule() {
        FizBuzzRule divisibleByTenRule = new FizBuzzRule(i -> i % 10 == 0, "TEN");
        Assertions.assertThat(divisibleByTenRule.getPredicate().test(10)).isEqualTo(true);
    }

}