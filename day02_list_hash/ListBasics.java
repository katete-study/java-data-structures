import java.util.*;

/**
 * ArrayList 기본 + 자주 틀리는 함정.
 * 실행: java -Dstdout.encoding=UTF-8 ListBasics.java
 */
public class ListBasics {
    public static void main(String[] args) {
        basics();
        removeTrap();
        listToArray();
        removeWhileLooping();
    }

    static void basics() {
        System.out.println("=== 기본 ===");
        List<Integer> list = new ArrayList<>();      // Kotlin: mutableListOf<Int>()
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1, 10);                             // 인덱스 1에 삽입 -> O(n)
        System.out.println(list);                    // [5, 10, 3, 8]

        System.out.println(list.get(2));             // 3      (Kotlin: list[2])
        list.set(0, 99);                             // Kotlin: list[0] = 99
        System.out.println(list.size());             // 4
        System.out.println(list.contains(8));        // true   (O(n)!)
        System.out.println(list.indexOf(8));         // 3, 없으면 -1

        Collections.sort(list);                      // 오름차순
        System.out.println(list);                    // [3, 8, 10, 99]
        list.sort(Collections.reverseOrder());       // 내림차순
        System.out.println(list);                    // [99, 10, 8, 3]

        List<String> names = List.of("a", "b");      // 수정 불가 리스트
        // names.add("c");                           // UnsupportedOperationException
        List<String> mutable = new ArrayList<>(names);   // 복사해서 수정 가능하게
        mutable.add("c");
        System.out.println(mutable);
    }

    static void removeTrap() {
        System.out.println("=== remove 함정 ===");
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 1));
        list.remove(1);                              // "인덱스 1" 삭제 -> 20 이 사라짐!
        System.out.println(list);                    // [10, 30, 1]
        list.remove(Integer.valueOf(1));             // "값 1" 삭제
        System.out.println(list);                    // [10, 30]
    }

    static void listToArray() {
        System.out.println("=== List <-> 배열 ===");
        List<Integer> list = new ArrayList<>(List.of(3, 1, 2));

        // List<Integer> -> int[]   (프로그래머스 반환용, 외워두기)
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(arr));    // [3, 1, 2]

        // int[] -> List<Integer>
        int[] src = {4, 5, 6};
        List<Integer> back = new ArrayList<>();
        for (int v : src) back.add(v);
        System.out.println(back);                    // [4, 5, 6]

        // String 리스트 <-> 배열
        List<String> words = new ArrayList<>(List.of("x", "y"));
        String[] wArr = words.toArray(new String[0]);
        System.out.println(Arrays.toString(wArr));   // [x, y]
    }

    static void removeWhileLooping() {
        System.out.println("=== 순회 중 삭제 ===");
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        try {
            for (int x : list) {
                if (x % 2 == 0) list.remove(Integer.valueOf(x));   // 예외 발생
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException 발생!");
        }
        list.removeIf(x -> x % 2 == 0);              // 올바른 방법
        System.out.println(list);                    // [1, 3, 5]
    }
}
