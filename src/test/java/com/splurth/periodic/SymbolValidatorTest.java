package com.splurth.periodic;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SymbolValidatorTest {

    private SymbolValidator symbolValidator = new SymbolValidator();

    ///// Word format validation
    @Test
    public void alphaOnlyWord() {
        assertTrue("alpha only word should pass", symbolValidator.alphaOnly.test("abcdefgABCDEFG"));
    }

    @Test
    public void alphaNumbericWord() {
        assertFalse("alphanumeric word should fail", symbolValidator.alphaOnly.test("abcdefg1234ABCDEFG"));
    }

    @Test
    public void alphaWordWithSpace() {
        assertFalse("alpha word with space should fail", symbolValidator.alphaOnly.test("be mine"));
    }

    ///// Symbol format validation
    @Test
    public void symbolOneLetter() {
        assertFalse("one letter symbol should not be a valid format", symbolValidator.validSymbolFormat.test("a"));
    }

    @Test
    public void symbolTwoLetters() {
        assertTrue("two letter symbol should be a valid format", symbolValidator.validSymbolFormat.test("ab"));
    }

    @Test
    public void symbolThreeLetters() {
        assertFalse("three letter symbol should not be a valid format", symbolValidator.validSymbolFormat.test("abc"));
    }

    @Test
    public void symbolWithNumeric() {
        assertFalse("symbol containing numeric should not be a valid format", symbolValidator.validSymbolFormat.test("a1"));
    }

    @Test
    public void symbolWithSpecialCharacter() {
        assertFalse("symbol containing special character should not be a valid format", symbolValidator.validSymbolFormat.test("a!"));
    }

    @Test
    public void symbolWithLeadingSpace() {
        assertFalse("symbol with leading space should not be a valid format", symbolValidator.validSymbolFormat.test(" a"));
    }

    @Test
    public void symbolWithTrailingSpace() {
        assertFalse("symbol with trailing space should not be a valid format", symbolValidator.validSymbolFormat.test("a "));
    }

    ///// Symbol in word validation
    @Test
    public void symbolCorrectOrder() {
        assertTrue("The symbol should have passed being that it's in the correct order for the given word",
                   symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Spenglerium", "ee"));
    }

    @Test
    public void symbolWithOneCharacter() {
        assertFalse("One character symbol is invalid", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Mercury", "m"));
    }

    @Test
    public void symbolIncorrectOrder() {
        assertFalse("The symbol should have failed being that it's in the wrong order for the given word",
                    symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Stantzon", "zt"));
    }

    @Test
    public void secondCharOfSymbolMissingFromWord() {
        assertFalse("The symbol should have failed because the second character does not exist in the word",
                    symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Tullium", "ty"));
    }

    @Test
    public void firstCharOfSymbolMissingFromWord() {
        assertFalse("The symbol should have failed because the first character does not exist in the word",
                    symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Headcheese", "ne"));
    }

    @Test
    public void casedSymbolMatching() {
        assertTrue("The cased symbol should have matched against an uncased word",
                   symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("zeddemorium", "ZR"));
    }

    @Test
    public void casedWordMatching() {
        assertTrue("The cased word should have been matched by the uncased symbol",
                   symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Zeddemorium", "zr"));
    }

    @Test
    public void duplicateCharacterSymbolOnceInWord() {
        assertFalse("Duplicate symbol character failed because the character is not duplicated in the word",
                    symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("details", "aa"));
    }

    @Test
    public void validateDuplicateCharacterSymbolTwiceInWord() {
        assertTrue("Duplicate character repeated in word is a valid symbol", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("repeat", "ee"));
    }

    @Test
    public void validateNumericWordAgainstCasedSymbol() {
        assertFalse("Word with numeric should have failed", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("abc1def", "ab"));
    }

    @Test
    public void validateNumericSymbolWithCasedWord() {
        assertFalse("Symbol with numeric should have failed", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("abcdef", "a1"));
    }

    @Test
    public void validateCasedWordWithUncasedSymbol() {
        assertTrue("Uncased symbol should have matched cased word", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("AbCdEfG", "bg"));
    }

    @Test
    public void validateCasedSymbolWithUncasedWord() {
        assertTrue("Cased symbol should have matched uncased word", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("abcdefg", "CF"));
    }

    @Test
    public void validateUncasedWordWithUncasedSymbol() {
        assertTrue("Uncased word and uncased symbol should have matched", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("abcdefg", "af"));
    }

    @Test
    public void validateCasedWordWithCasedSymbol() {
        assertTrue("Cased word and cased symbol should have matched", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("AbCdEfG", "bE"));
    }

    @Test
    public void validateSpacedWordWithCasedSymbol() {
        assertFalse("Word with space is not valid", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("clinton trump", "Bm"));
    }

    @Test
    public void validateLeadingSpaceSymbolWithCasedWord() {
        assertFalse("Leading space symbol is invalid", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("ObamaCare", " O"));
    }

    @Test
    public void validateTrailingSpaceSymbolWithCasedWord() {
        assertFalse("Trailing space symbol is invalid", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Freedom", "F "));
    }

    @Test
    public void validateCasedWordWithSingleCharSymbol() {
        assertFalse("Single char symbol is invalid", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("Pokemon", "k"));
    }

    @Test
    public void validateWrongSymbolOrder() {
        assertFalse("Incorrect order of symbol should fail", symbolValidator.validSymbolOrderAndNumberOfOccurrences.test("PokemonGo", "GP"));
    }
}