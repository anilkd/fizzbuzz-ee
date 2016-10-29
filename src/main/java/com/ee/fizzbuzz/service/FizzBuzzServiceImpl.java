package com.ee.fizzbuzz.service;

import com.ee.fizzbuzz.rules.FizzBuzzPredicateFactory;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzzServiceImpl implements FizzBuzzService {


    private static final String DELIMITER = " ";
    private final Map<Predicate<Integer>, String> predicateMap;

   
    public FizzBuzzServiceImpl() {
        this(FizzBuzzPredicateFactory.getPredicateMap());
    }

    /**
     * 
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
