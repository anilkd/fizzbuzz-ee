package com.ee.fizzbuzz.service;

import com.ee.fizzbuzz.rules.FizBuzzRule;
import com.ee.fizzbuzz.rules.FizzBuzzRuleService;
import com.ee.fizzbuzz.rules.FizzBuzzRuleServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;

public class FizzBuzzServiceImpl implements FizzBuzzService {


    private static final String DELIMITER = " ";
    public static final String DELIMITER_2 = ":";
    public static final String NEW_LINE = "\n";
    public static final String INTEGER = "integer";
    public static final String IS_DIGIT = "[0-9]+";

    private FizzBuzzRuleService<FizBuzzRule<Integer, String>> ruleService;


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
                .map(this::isWord)
                .collect(Collectors.groupingBy(result -> result, counting()));

        String groupByReport = reportMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + DELIMITER_2 + entry.getValue())
                .collect(Collectors.joining(NEW_LINE));

        return finalReport.append(parsedText).append(NEW_LINE).append(groupByReport).toString();
    }

    private String isWord(String input) {
        return input.matches(IS_DIGIT) ? INTEGER : input;
    }

    private String convert(int number) {
        return getRulesGroupByPredicate()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(number))
                .findAny()
                .map(Map.Entry::getValue)
                .orElse(String.valueOf(number));
    }

    private Map<Predicate<Integer>, String> getRulesGroupByPredicate() {
        return ruleService.getRules()
                .stream()
                .collect(Collectors.toMap(FizBuzzRule::getPredicate,
                        FizBuzzRule::getResult,
                        (k, v) -> k,
                        LinkedHashMap::new));

    }

    private boolean validateInput(int start, int end) {
        boolean isValidInput = start > 0 && start <= end;
        if (!isValidInput) {
            throw new IllegalArgumentException("Invalid input with range start:" + start + " end:" + end);
        }
        return true;
    }
}
