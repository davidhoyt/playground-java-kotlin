package playground;

import static playground.Utils.println;

public class URLify {
    // Replace all spaces with "%20"
    public static String work(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    sb.append("%20");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        println(work("abcd"));
        println(work(" abcd"));
        println(work("abcd e"));
        println(work("abcd"));
    }
}
