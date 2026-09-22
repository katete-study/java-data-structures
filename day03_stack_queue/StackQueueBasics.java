import java.util.*;

/**
 * Deque 로 스택/큐 둘 다 처리하기 + 자주 나오는 패턴.
 * 실행: java -Dstdout.encoding=UTF-8 StackQueueBasics.java
 */
public class StackQueueBasics {
    public static void main(String[] args) {
        stackBasics();
        queueBasics();
        System.out.println("발레 파킹((()) : " + isValidParentheses("(()())"));   // true
        System.out.println("발레 파킹())( : " + isValidParentheses("())("));      // false
        System.out.println(Arrays.toString(nextGreater(new int[]{2, 1, 3, 4, 1}))); // [3, 3, 4, -1, -1]
        System.out.println(reverseString("hello"));   // olleh
    }

    static void stackBasics() {
        System.out.println("=== 스택 (Deque, 앞쪽 사용) ===");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);          // [3, 2, 1]  (맨 앞이 top)
        System.out.println(stack.peek());   // 3 (제거 안 함)
        System.out.println(stack.pop());    // 3
        System.out.println(stack.pop());    // 2
        System.out.println(stack.isEmpty()); // false
        System.out.println(stack.pop());    // 1
        System.out.println(stack.isEmpty()); // true

        try {
            stack.pop();                     // 빈 스택에서 pop -> 예외
        } catch (NoSuchElementException e) {
            System.out.println("빈 스택 pop -> NoSuchElementException");
        }
    }

    static void queueBasics() {
        System.out.println("=== 큐 (Deque, 뒤에 넣고 앞에서 빼기) ===");
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue);          // [1, 2, 3]  (맨 앞이 먼저 나갈 원소)
        System.out.println(queue.peek());   // 1
        System.out.println(queue.poll());   // 1
        System.out.println(queue.poll());   // 2
        System.out.println(queue.poll());   // 3
        System.out.println(queue.poll());   // null (빈 큐, 예외 아님!)
    }

    // 괄호 짝 검사 - 스택의 가장 기본 활용
    static boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 각 원소 기준, 오른쪽에서 처음 나오는 자신보다 큰 값 (없으면 -1) - 스택 활용의 대표 유형
    static int[] nextGreater(int[] arr) {
        int[] result = new int[arr.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();   // 인덱스를 저장
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }

    // 스택 응용: 문자열 뒤집기 (책 Ch6 05-1)
    static String reverseString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) stack.push(c);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }
}
