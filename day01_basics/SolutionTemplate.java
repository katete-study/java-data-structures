import java.util.Arrays;

/**
 * 프로그래머스 제출 형태 + 로컬 테스트용 main.
 * 프로그래머스에는 `class Solution { ... }` 부분만 붙여넣으면 됩니다. (main 은 빼기)
 * 실행: java SolutionTemplate.java
 */
class Solution {
    // 예시: 평균 구하기 (프로그래머스 Lv.1)
    public double solution(int[] arr) {
        double sum = 0;
        for (int v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }
}

public class SolutionTemplate {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{1, 2, 3, 4}));   // 2.5
        System.out.println(sol.solution(new int[]{5, 5}));         // 5.0
    }
}
