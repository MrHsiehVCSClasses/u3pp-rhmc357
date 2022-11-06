package u3pp;

public class PalindromeTester {
    /**z
     * Tests whether a string is a palindrome. Case insensitive. 
     * @param s  the string to test
     * @return true if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        int OGIndex = 0;
        int space = 0;
        
        for (int i = s.length(); i > 0; i--){
            if (s.charAt(i - 1) == ' '){
                space = space + 1;
            }
            else if (Character.toLowerCase(s.charAt(i-1)) == Character.toLowerCase(s.charAt(OGIndex))){
                OGIndex += 1;
            }
            else {
                return false;
            }
            
        }

        return true;
    }
}
