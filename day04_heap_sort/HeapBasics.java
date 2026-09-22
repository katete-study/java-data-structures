import java.util.*;

/**
 * PriorityQueue / Comparator 기본 + 코테 단골 패턴.
 * 실행: java -Dstdout.encoding=UTF-8 HeapBasics.java
 */
public class HeapBasics {
    public static void main(String[] args) {
        minHeapBasics();
        maxHeapBasics();
        comparatorBasics();
        System.out.println("가장 작은 3개: " + smallestK(new int[]{9, 1, 7, 3, 5, 2}, 3));
        System.out.println("더 맵게 결과 (K=7 넘을 때까지 필요한 섞기 횟수): " + moreSpicy(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    static void minHeapBasics() {
        System.out.println("=== 최소 힙 (기본) ===");
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(3);
        pq.offer(2);
        System.out.println(pq.peek());   // 1 (보기만, 제거 안 함)
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");   // 1 2 3 5  (항상 오름차순으로 나옴)
        }
        System.out.println();
    }

    static void maxHeapBasics() {
        System.out.println("=== 최대 힙 (Comparator 뒤집기) ===");
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : new int[]{5, 1, 3, 2}) maxHeap.offer(v);
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");   // 5 3 2 1  (항상 내림차순)
        }
        System.out.println();

        // 람다로 직접 작성 (b - a 면 내림차순)
        Queue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);
        for (int v : new int[]{5, 1, 3, 2}) maxHeap2.offer(v);
        System.out.println(maxHeap2.poll());   // 5
    }

    static void comparatorBasics() {
        System.out.println("=== Comparator ===");
        List<String> words = new ArrayList<>(List.of("banana", "kiwi", "fig", "apple"));

        words.sort((a, b) -> a.length() - b.length());   // 길이 오름차순
        System.out.println(words);   // [fig, kiwi, apple, banana] (fig=3, kiwi=4, apple/banana=5,6... 길이순)

        words.sort(Comparator.comparingInt(String::length).reversed());   // 길이 내림차순
        System.out.println(words);

        // 여러 기준: 길이 -> 같으면 사전순
        List<String> mix = new ArrayList<>(List.of("bb", "aa", "c", "dd"));
        mix.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        System.out.println(mix);   // [c, aa, bb, dd]

        // 2차원 배열: 두 번째 값 기준
        int[][] arr = {{1, 5}, {2, 1}, {3, 3}};
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        System.out.println(Arrays.deepToString(arr));   // [[2, 1], [3, 3], [1, 5]]
    }

    // 힙에 넣고 K번 꺼내기 - "가장 작은/큰 K개" 패턴
    static List<Integer> smallestK(int[] arr, int k) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int v : arr) pq.offer(v);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) result.add(pq.poll());
        return result;
    }

    // 프로그래머스 "더 맵게" 문제의 핵심 로직만 축약 (교육용)
    // 가장 안 매운 두 음식을 섞어(scoville[min1] + scoville[min2]*2) K 이상으로 만들 때까지 반복
    static int moreSpicy(int[] scoville, int k) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int v : scoville) pq.offer(v);
        int count = 0;
        while (pq.peek() != null && pq.peek() < k) {
            if (pq.size() < 2) return -1;   // 더 이상 섞을 수 없음
            int first = pq.poll();
            int second = pq.poll();
            pq.offer(first + second * 2);
            count++;
        }
        return count;
    }
}
