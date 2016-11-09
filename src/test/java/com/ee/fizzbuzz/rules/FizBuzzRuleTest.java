package com.ee.fizzbuzz.rules;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FizBuzzRuleTest {
    
    @Test
    public void shouldSuccessfullyCreateRule(){
        FizBuzzRule<Integer,String> divisibleByTenRule=new FizBuzzRule<Integer, String>(i-> i%10==0,"TEN");
        Assertions.assertThat(divisibleByTenRule).isEqualToComparingFieldByField(new FizBuzzRule<Integer, String>(i-> i%10==0,"TEN"));
        
    }

}