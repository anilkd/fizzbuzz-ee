package com.ee.fizzbuzz.service;

import com.ee.fizzbuzz.rules.FizzBuzzPredicateFactory;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;

public class FizzBuzzServiceImpl implements FizzBuzzService {


    private static final String DELIMITER = " ";
    public static final String DELIMETER_2 = ":";
    public static final String NEW_LINE = "\n";
    private final Map<Predicate<Integer>, String> predicateMap;


    public FizzBuzzServiceImpl() {
        this(FizzBuzzPredicateFactory.getPredicateMap());
    }

    /**
     * @param predicateMap map of predicate and results
     */
    public FizzBuzzServiceImpl(Map<Predicate<Integer>, String> predicateMap) {
        this.predicateMap = predicateMap;
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
                .map(entry -> entry.getKey() + DELIMETER_2 + entry.getValue())
                .collect(Collectors.joining(NEW_LINE));

        return finalReport.append(parsedText).append(NEW_LINE).append(groupByReport).toString();
    }

    private String isWord(String input) {
        return input.matches("[0-9]+") ? "integer" : input;
    }

    private String convert(int number) {
        return predicateMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(number))
                .findAny()
                .map(Map.Entry::getValue)
                .orElse(String.valueOf(number));
    }

    private boolean validateInput(int start, int end) {
        boolean isValidInput = start > 0 && start <= end;
        if (!isValidInput) {
            throw new IllegalArgumentException("Invalid input with range start:" + start + " end:" + end);
        }
        return true;
    }
}
