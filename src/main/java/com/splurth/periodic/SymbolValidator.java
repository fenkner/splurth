package com.splurth.periodic;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

final class SymbolValidator {

    Predicate<String> alphaOnly = s -> Pattern.compile("^[a-z]*$", Pattern.CASE_INSENSITIVE).matcher(s).matches();
    Predicate<String> validSymbolFormat = symbol -> Pattern.compile("^[a-z]{2}", Pattern.CASE_INSENSITIVE).matcher(symbol).matches();
    BiPredicate<String, String> validSymbolOrderAndNumberOfOccurrences = (word, symbol) -> {

        if (!alphaOnly.test(word) || ! validSymbolFormat.test(symbol)) {
            return false;
        }

        word = word.toLowerCase();
        symbol = symbol.toLowerCase();
        int char1Index = word.indexOf(symbol.charAt(0));
        int char2Index = word.lastIndexOf(symbol.charAt(1));

        return char1Index != -1 && char2Index != -1 && char2Index > char1Index;
    };

}
