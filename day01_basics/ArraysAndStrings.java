import java.util.Arrays;

/**
 * 코테에서 매일 쓰는 배열/문자열 API 모음.
 * 실행: java ArraysAndStrings.java
 */
public class ArraysAndStrings {
    public static void main(String[] args) {
        arrays();
        strings();
        stringBuilder();
        conversions();
    }

    static void arrays() {
        System.out.println("=== 배열 ===");
        int[] a = {5, 2, 9, 1};

        Arrays.sort(a);                                   // 오름차순, O(n log n)
        System.out.println(Arrays.toString(a));           // [1, 2, 5, 9]

        int[] filled = new int[5];
        Arrays.fill(filled, -1);                          // 전체를 -1로
        System.out.println(Arrays.toString(filled));

        int[] copy = Arrays.copyOf(a, 6);                 // 길이 6으로 복사(부족분 0)
        int[] part = Arrays.copyOfRange(a, 1, 3);         // [1, 3) -> [2, 5]
        System.out.println(Arrays.toString(copy) + " " + Arrays.toString(part));

        int[][] grid = new int[3][4];                     // 3행 4열, 0으로 초기화
        grid[1][2] = 7;
        System.out.println(Arrays.deepToString(grid));    // 2차원은 deepToString
        System.out.println(grid.length + " x " + grid[0].length);   // 행, 열

        // 내림차순은 int[]에는 바로 못 씀. Integer[] 필요 (Day 4에서 자세히)
        Integer[] boxed = {5, 2, 9, 1};
        Arrays.sort(boxed, (x, y) -> y - x);
        System.out.println(Arrays.toString(boxed));       // [9, 5, 2, 1]

        System.out.println(Arrays.stream(a).sum());       // 17
        System.out.println(Arrays.stream(a).max().getAsInt());  // 9
    }

    static void strings() {
        System.out.println("=== 문자열 ===");
        String s = "Hello World";
        System.out.println(s.length());                   // 11
        System.out.println(s.charAt(4));                  // o
        System.out.println(s.substring(0, 5));            // Hello  (끝 인덱스 미포함)
        System.out.println(s.substring(6));               // World
        System.out.println(s.indexOf("World"));           // 6, 없으면 -1
        System.out.println(s.toUpperCase() + " " + s.toLowerCase());
        System.out.println(s.contains("lo W"));           // true
        System.out.println(s.replace("l", "L"));          // HeLLo WorLd

        String[] words = "a b  c".split(" ");             // 연속 공백은 빈 문자열이 생김
        System.out.println(words.length);                 // 4 ("a","b","","c")
        System.out.println(Arrays.toString("a1b2c3".split("")));   // 한 글자씩

        char[] cs = s.toCharArray();                      // 문자 배열로 바꿔 수정
        Arrays.sort(cs);
        System.out.println(new String(cs).trim());        // 정렬된 문자열

        System.out.println(String.join("-", "a", "b", "c"));   // a-b-c
        System.out.println("abc".compareTo("abd"));       // 음수 (사전순 비교)
    }

    static void stringBuilder() {
        System.out.println("=== StringBuilder ===");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i).append(',');                     // 반복 이어붙이기는 반드시 이걸로
        }
        sb.setLength(sb.length() - 1);                    // 마지막 ',' 제거
        System.out.println(sb);                           // 0,1,2,3,4
        System.out.println(sb.reverse());                 // 4,3,2,1,0
        System.out.println(new StringBuilder("abc").reverse().toString());  // cba
    }

    static void conversions() {
        System.out.println("=== 변환 ===");
        int n = Integer.parseInt("123");                  // String -> int
        String s = String.valueOf(n);                     // int -> String
        String s2 = n + "";                               // 간단한 방법
        System.out.println(n + 1 + " " + s + s2);         // 124 123123

        System.out.println(Integer.toBinaryString(10));       // 1010
        System.out.println(Integer.parseInt("1010", 2));      // 10
        System.out.println(Integer.toString(255, 16));        // ff

        char d = '7';
        System.out.println(d - '0');                      // 7
        System.out.println(Character.isDigit(d) + " " + Character.isLetter('a'));
        System.out.println((char) ('a' + 2));             // c

        long total = Integer.MAX_VALUE;
        total += 1;
        System.out.println(total);                        // 2147483648
        System.out.println(Math.pow(2, 10));              // 1024.0 (double!)
        System.out.println((int) Math.pow(2, 10));        // 1024
    }
}
