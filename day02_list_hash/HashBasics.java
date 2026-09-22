import java.util.*;

/**
 * HashMap / HashSet 기본 + 코테 단골 패턴.
 * 실행: java -Dstdout.encoding=UTF-8 HashBasics.java
 */
public class HashBasics {
    public static void main(String[] args) {
        mapBasics();
        setBasics();
        frequency();
        grouping();
        System.out.println("합이 10인 쌍 존재: " + hasPairWithSum(new int[]{1, 4, 6, 8}, 10));   // true (4+6)
        System.out.println("합이 100인 쌍 존재: " + hasPairWithSum(new int[]{1, 4, 6, 8}, 100)); // false
    }

    static void mapBasics() {
        System.out.println("=== HashMap 기본 ===");
        Map<String, Integer> map = new HashMap<>();      // Kotlin: mutableMapOf<String, Int>()
        map.put("apple", 3);                             // Kotlin: map["apple"] = 3
        map.put("banana", 5);
        map.put("apple", 4);                             // 같은 키는 덮어씀

        System.out.println(map.get("apple"));            // 4
        System.out.println(map.get("kiwi"));             // null  (없는 키)
        System.out.println(map.getOrDefault("kiwi", 0)); // 0
        System.out.println(map.containsKey("banana"));   // true
        System.out.println(map.size());                  // 2

        // 순회: entrySet
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());   // 순서 보장 X
        }
        // 키만 / 값만
        for (String k : map.keySet()) { /* ... */ }
        for (int v : map.values()) { /* ... */ }

        // 함정: 없는 키를 int 로 언박싱하면 NPE
        try {
            int x = map.get("kiwi");
        } catch (NullPointerException e) {
            System.out.println("NPE 발생! -> getOrDefault 를 쓰세요");
        }
    }

    static void setBasics() {
        System.out.println("=== HashSet 기본 ===");
        Set<Integer> set = new HashSet<>();
        System.out.println(set.add(1));                  // true  (새로 추가됨)
        System.out.println(set.add(1));                  // false (이미 있음)
        set.add(2);
        System.out.println(set.contains(2));             // true  O(1)
        System.out.println(set.size());                  // 2

        int[] arr = {3, 1, 3, 2, 1, 2};
        Set<Integer> uniq = new HashSet<>();
        for (int v : arr) uniq.add(v);
        System.out.println("서로 다른 값의 개수: " + uniq.size());   // 3
    }

    static void frequency() {
        System.out.println("=== 빈도수 세기 ===");
        String s = "abracadabra";

        // 방법 1: getOrDefault
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        // 방법 2: merge (더 짧음)
        Map<Character, Integer> freq2 = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq2.merge(c, 1, Integer::sum);
        }
        System.out.println(new TreeMap<>(freq));         // {a=5, b=2, c=1, d=1, r=2}  (TreeMap 으로 정렬해서 출력)
        System.out.println(freq.equals(freq2));          // true

        // 알파벳 소문자만이라면 배열이 더 빠르고 간단
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        System.out.println(Arrays.toString(cnt));
    }

    static void grouping() {
        System.out.println("=== 그룹핑 ===");
        String[] words = {"apple", "avocado", "banana", "blueberry", "cherry"};
        Map<Character, List<String>> groups = new HashMap<>();
        for (String w : words) {
            groups.computeIfAbsent(w.charAt(0), k -> new ArrayList<>()).add(w);
        }
        System.out.println(new TreeMap<>(groups));       // {a=[apple, avocado], b=[banana, blueberry], c=[cherry]}
    }

    // Day 1 의 O(n^2) 문제를 O(n) 으로: "합이 target 인 두 수가 있는가?"
    static boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int v : arr) {
            if (seen.contains(target - v)) return true;  // 필요한 짝이 이미 나왔는지 O(1) 확인
            seen.add(v);
        }
        return false;
    }
}
