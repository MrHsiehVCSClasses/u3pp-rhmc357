package u3pp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.Test;

class PalindromeTesterTests {

    @Test 
    void isPalindrome_whenCalledWithSingleCharacterString_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("n"));
    }

    @Test
    void isPalindrome_whenCalledWithAllUpperCaseOddPalindrome_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("XYZYX"));
    }

    @Test
    void isPalindrome_whenCalledWithAllUpperCaseEvenPalindrome_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("QWERREWQ"));
    }

    @Test
    void isPalindrome_whenCalledWithAllLowerCaseOddPalindrome_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("abcdcba"));
    }

    @Test
    void isPalindrome_whenCalledWithAllLowerCaseEvenPalindrome_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("abccba"));
    }

    @Test
    void isPalindrome_whenCalledWithAllLowerCaseLongPalindrome_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("aaaabbccddeeddeeddggaaddggddaaaeeeeppeeeeaaaddggddaaggddeeddeeddccbbaaaa"));
    }

    @Test
    void isPalindrome_whenCalledWithAllLowerCaseLongLongPalindrome_returnsTrue() {
        String input = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < 20; i++) {
            input += input;
        }
        input += new StringBuilder(input).reverse().toString();
        // input.length() is 54,525,952
        assertTrue(PalindromeTester.isPalindrome(input));
    }

    @Test
    void isPalindrome_whenCalledWithSpacesAndCapitalizationDifferences_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("NeverOddOrEven"));
    }

    @Test
    void isPalindrome_whenCalledWithASingleWordPalindromeWithCapitalizationDifferences_returnsTrue() {
        assertTrue(PalindromeTester.isPalindrome("raCecAr"));
    }
    
    @Test
    void isPalindrome_whenCalledWithASimpleStringThatIsNotAPalindrome_returnsFalse() {
        assertFalse(PalindromeTester.isPalindrome("thisISnoTApalindrome"));
    }

    @Test
    void isPalindrome_whenCalledWithAStringWithSpacesAndSymbolsThatIsNotAPalindrome_returnsFalse() {
        assertFalse(PalindromeTester.isPalindrome("thi@@isIsNot       a Palindrome"));
    } 
}