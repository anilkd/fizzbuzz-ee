package com.ee.fizzbuzz.service;

import com.ee.fizzbuzz.rules.FizBuzzRule;
import com.ee.fizzbuzz.rules.FizzBuzzRuleService;
import com.ee.fizzbuzz.rules.FizzBuzzRuleServiceImpl;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;

public class FizzBuzzServiceImpl implements FizzBuzzService {

    private static final String DELIMITER = " ";
    private static final String DELIMITER_2 = ":";
    private static final String NEW_LINE = "\n";
    private static final String INTEGER = "integer";
    private static final String IS_DIGIT = "[0-9]+";

    private FizzBuzzRuleService<FizBuzzRule> ruleService;

    public FizzBuzzServiceImpl() {
        this.ruleService = new FizzBuzzRuleServiceImpl();
    }

    @Override
    public String parseNumbers(int start, int end) {
        validateInput(start, end);
        return IntStream.rangeClosed(start, end)
                        .mapToObj(this::convert)
                        .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public String generateReport(int start, int end) {
        StringBuilder finalReport = new StringBuilder();
        String parsedText = parseNumbers(start, end);
        Map<String, Long> reportMap = asList(parsedText.split(DELIMITER)).stream()
                                                                         .map(FizzBuzzServiceImpl::isWord)
                                                                         .collect(Collectors.groupingBy(result -> result, counting()));

        String groupByReport = reportMap.entrySet()
                                        .stream()
                                        .map(entry -> entry.getKey() + DELIMITER_2 + entry.getValue())
                                        .collect(Collectors.joining(NEW_LINE));

        return finalReport.append(parsedText).append(NEW_LINE).append(groupByReport).toString();
    }

    private static String isWord(String input) {
        return input.matches(IS_DIGIT) ? INTEGER : input;
    }

    private String convert(int number) {
        return ruleService.getRules()
                          .stream()
                          .filter(rule -> rule.getPredicate().test(number))
                          .findAny()
                          .map(FizBuzzRule::getResult)
                          .orElse(String.valueOf(number));
    }

    private static boolean validateInput(int start, int end) {
        boolean isValidInput = start > 0 && start <= end;
        if (!isValidInput) {
            throw new IllegalArgumentException("Invalid input with range start:" + start + " end:" + end);
        }
        return true;
    }
}
