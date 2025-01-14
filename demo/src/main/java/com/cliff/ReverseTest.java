package com.cliff;

public class ReverseTest {
    public int reverse(Integer num) {
        int reversed = 0;
        while (num > 0) {
            int currentDigit = num % 10;
            reversed = reversed * 10 + currentDigit;
            num /= 10;
        }

        return reversed;
    }

    public String reverse(String input, boolean useSB) {
        StringBuilder needToReverse = new StringBuilder();
        needToReverse.append(input);
        return needToReverse.reverse().toString();
    }

    public String reverse(String input) {
        char[] needReverse = input.toCharArray();
        int left = 0, right = needReverse.length-1;
        while (left < right) {
            char temp = needReverse[right];
            needReverse[right] = needReverse[left];
            needReverse[left] = temp;
            left++;
            right--;
        }
        return String.valueOf(needReverse);
    }
}
