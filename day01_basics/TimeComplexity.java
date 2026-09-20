/**
 * O(n) 과 O(n²) 의 차이를 눈으로 확인하기.
 * 실행: java TimeComplexity.java
 *
 * 문제: 배열에서 합이 target 이 되는 두 수가 있는가?
 * N = 20,000 정도만 돼도 O(n²)는 느려지기 시작합니다.
 * (N = 100,000 이면 약 50억 번 -> 시간초과. 그래서 Day 2의 HashSet 이 필요합니다)
 */
public class TimeComplexity {
    public static void main(String[] args) {
        int n = 30_000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2;            // 전부 짝수 -> 홀수 target 은 절대 못 만듦(최악의 경우)
        }
        int target = 1;

        long start = System.nanoTime();
        boolean found = hasPairQuadratic(arr, target);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("O(n^2): found=" + found + ", " + ms + "ms, 반복 횟수 약 " + (long) n * n / 2);

        start = System.nanoTime();
        long ops = countLinear(arr);
        ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("O(n)  : sum=" + ops + ", " + ms + "ms, 반복 횟수 " + n);

        // 직접 해보기: n 을 10만으로 바꾸면 O(n²) 시간이 어떻게 변하는지 확인하세요.
        // 반복 횟수가 (10만/3만)² ≈ 11배 증가합니다.

        System.out.println("log2 예시: 1,000,000 을 반으로 나누는 횟수 = " + halvingSteps(1_000_000));
    }

    // O(n²): 모든 쌍을 확인
    static boolean hasPairQuadratic(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n): 한 번만 순회
    static long countLinear(int[] arr) {
        long sum = 0;
        for (int v : arr) {
            sum += v;
        }
        return sum;
    }

    // O(log n): 절반씩 줄어듦 (이분탐색이 빠른 이유)
    static int halvingSteps(int n) {
        int steps = 0;
        while (n > 1) {
            n /= 2;
            steps++;
        }
        return steps;    // 약 log2(n)
    }
}
