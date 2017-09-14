package playground;

//https://leetcode.com/problems/zigzag-conversion/description/

//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//And then read line by line: "PAHNAPLSIIGYIR"
//Write the code that will take a string and make this conversion given a number of rows:
//
//string convert(string text, int nRows);
//convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

public class zigzag {
    public String convert(String in, int numberOfRows) {
        if (numberOfRows < 1)
            return "";
        else if (numberOfRows == 1)
            return in;
        char[] chars = in.toCharArray();
        StringBuilder[] rows = new StringBuilder[numberOfRows];
        
        for (int i = 0; i < rows.length; ++i)
            rows[i] = new StringBuilder();
        
        int rowIndex = 0;
        boolean down = true;

        for (int i = 0; i < chars.length; ++i) {
            rows[rowIndex].append(chars[i]);
            if (down && rowIndex == numberOfRows - 1) {
                down = false;
            }
            if (!down && rowIndex == 0)
                down = true;
            if (down) {
                rowIndex++;
            } else {
                rowIndex--;
            }
        }

        StringBuilder sb = new StringBuilder(chars.length);
        for (int i = 0; i < numberOfRows; ++i) {
            sb.append(rows[i].toString());
        }

        return sb.toString();
    }

    public void run() {
        System.out.println(convert("AB", 1));
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static void main(String[] args) {
        new zigzag().run();
    }
}
