/**
 * Kotlin 사용자를 위한 Java 문법 비교 예제.
 * 실행: java KotlinToJava.java   (JDK 11 이상, 컴파일 없이 바로 실행)
 */
public class KotlinToJava {
    public static void main(String[] args) {
        // 1) 변수: 타입이 앞, 세미콜론 필수
        int a = 10;               // Kotlin: var a = 10
        final int b = 3;          // Kotlin: val b = 3
        System.out.println("a=" + a + ", b=" + b);   // Kotlin: println("a=$a, b=$b")

        // 2) 정수 나눗셈 / 실수 나눗셈
        System.out.println(a / b);            // 3 (정수)
        System.out.println((double) a / b);   // 3.3333333333333335
        System.out.println(a % b);            // 1

        // 3) int 오버플로 -> long
        int big = 2_000_000_000;
        System.out.println(big + big);         // 오버플로: -294967296
        System.out.println((long) big + big);  // 4000000000
        long safe = 1L * 100_000 * 100_000;    // 곱하기 전에 long으로 만들기
        System.out.println(safe);

        // 4) 문자열 비교: == 는 주소 비교!
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1 == s2);          // false (다른 객체)
        System.out.println(s1.equals(s2));     // true  <- 항상 이걸 사용

        // 5) Integer 박싱 함정 (제네릭/컬렉션에서 만나게 됨)
        Integer x = 127, y = 127;
        Integer p = 1000, q = 1000;
        System.out.println(x == y);            // true  (-128~127 캐시)
        System.out.println(p == q);            // false (!)
        System.out.println(p.equals(q));       // true

        // 6) 반복문
        for (int i = 0; i < 3; i++) {          // Kotlin: for (i in 0 until 3)
            System.out.print(i + " ");
        }
        System.out.println();

        int[] arr = {5, 3, 8};
        for (int v : arr) {                    // Kotlin: for (v in arr)
            System.out.print(v + " ");
        }
        System.out.println();

        // 7) 삼항 연산자 (Kotlin의 if 식)
        String parity = (a % 2 == 0) ? "짝수" : "홀수";
        System.out.println(parity);

        // 8) switch: break 필수 (없으면 fall-through)
        int day = 2;
        switch (day) {
            case 1:
                System.out.println("월");
                break;
            case 2:
                System.out.println("화");
                break;
            default:
                System.out.println("기타");
        }

        // 9) char 산술
        char c = 'a';
        System.out.println((char) (c + 1));    // b   (int로 변하므로 캐스팅 필요)
        System.out.println('7' - '0');         // 7   (숫자 문자 -> int)

        // 10) 배열의 길이는 length (괄호 없음)
        System.out.println(arr.length);
        System.out.println("hello".length());  // String은 length()

        // 11) 배열은 참조 타입: 그냥 println 하면 주소가 나옴
        System.out.println(arr);                        // [I@해시 (쓸모없음)
        System.out.println(java.util.Arrays.toString(arr));  // [5, 3, 8]

        // 12) String.format
        System.out.println(String.format("%d + %d = %d", 1, 2, 3));
        System.out.println(String.format("%.2f", 3.14159));   // 3.14
    }
}
