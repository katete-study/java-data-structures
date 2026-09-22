# Day 2. ArrayList / HashMap / HashSet + 제네릭

> 목표: **"중복 확인, 개수 세기, 빠른 조회"를 O(1)로 처리하는 해시**를 익히고, List/Map/Set을 Kotlin 대응으로 바로 쓰기

## 0. 오늘의 순서 (책 + 실습)

| 순서 | 할 일 | 자료 |
|---|---|---|
| 1 | 책 Ch4 **04 제네릭** | p.84~86 (`<T>`가 뭔지만 이해하면 됨) |
| 2 | 책 Ch5 리스트 **배열 리스트** | p.100~125 (개념 + 객체 구조 + 작업. 구현 코드는 훑기), 04 비교 p.148 |
| 3 | 책 Ch12 해시 | p.418~427 (해시란, 해시 함수, 체이닝 개념까지) |
| 4 | 예제 코드 실행 | `ListBasics.java`, `HashBasics.java` |
| 5 | 문제 풀이 | `problems.md` |

VisuAlgo에서 **Hash Table**을 열어놓고 체이닝이 어떻게 동작하는지 한 번만 눈으로 보세요.

## 1. 제네릭과 컬렉션 선언

```java
List<Integer> list = new ArrayList<>();        // Kotlin: mutableListOf<Int>()
Map<String, Integer> map = new HashMap<>();    // Kotlin: mutableMapOf<String, Int>()
Set<Integer> set = new HashSet<>();            // Kotlin: mutableSetOf<Int>()
```

- 왼쪽은 **인터페이스**(`List`, `Map`, `Set`), 오른쪽은 **구현체**(`ArrayList`, `HashMap`, `HashSet`)로 씁니다.
- `<>` 안에는 **기본형(`int`) 불가**, 래퍼 클래스(`Integer`, `Long`, `Character`, `Double`, `Boolean`)를 씁니다.
- import 필요: `import java.util.*;` (코테에서는 이 한 줄로 충분)

## 2. Kotlin → Java 대응표

### List

| 동작 | Kotlin | Java |
|---|---|---|
| 추가 | `list.add(x)` | `list.add(x)` |
| 읽기 | `list[i]` | `list.get(i)` |
| 수정 | `list[i] = x` | `list.set(i, x)` |
| 크기 | `list.size` | `list.size()` |
| 삭제 | `list.removeAt(i)` | `list.remove(i)` ⚠️ (아래 함정 참고) |
| 포함 여부 | `x in list` | `list.contains(x)` (**O(n)**) |
| 정렬 | `list.sort()` | `Collections.sort(list)` 또는 `list.sort(null)` |
| 초기값 | `listOf(1,2,3)` | `List.of(1,2,3)` (수정 불가) / `new ArrayList<>(List.of(1,2,3))` |

### Map (HashMap)

| 동작 | Kotlin | Java |
|---|---|---|
| 저장 | `map[k] = v` | `map.put(k, v)` |
| 조회 | `map[k]` (null 가능) | `map.get(k)` (**없으면 null**) |
| 기본값 조회 | `map.getOrDefault(k, 0)` | `map.getOrDefault(k, 0)` |
| 키 존재 | `k in map` | `map.containsKey(k)` |
| 삭제 | `map.remove(k)` | `map.remove(k)` |
| 개수 세기 | `map[k] = (map[k] ?: 0) + 1` | `map.put(k, map.getOrDefault(k, 0) + 1)` 또는 `map.merge(k, 1, Integer::sum)` |
| 순회 | `for ((k, v) in map)` | `for (Map.Entry<K,V> e : map.entrySet())` → `e.getKey()`, `e.getValue()` |

### Set (HashSet)

`set.add(x)` (이미 있으면 **false 반환**), `set.contains(x)` (O(1)), `set.remove(x)`, `set.size()`

## 3. 함정 6가지

1. **`list.remove(int)` vs `list.remove(Object)`**
   `List<Integer>`에서 `list.remove(1)`은 "값 1 삭제"가 아니라 **"인덱스 1 삭제"**입니다.
   값을 지우려면 `list.remove(Integer.valueOf(1))`.
2. **`map.get(k)`가 null → 언박싱하면 NPE**
   `int c = map.get("x");` 키가 없으면 `NullPointerException`. → `getOrDefault` 사용.
3. **HashMap/HashSet은 순서가 보장되지 않습니다.** 입력 순서가 필요하면 `LinkedHashMap`, 정렬이 필요하면 `TreeMap`(Day 5).
4. **순회 중 삭제 금지**: `for (x : list) list.remove(...)` → `ConcurrentModificationException`. 인덱스 역순 for문이나 `removeIf` 사용.
5. **`List<Integer>` → `int[]`**: `list.stream().mapToInt(Integer::intValue).toArray()`. 프로그래머스에서 반환 타입이 `int[]`일 때 자주 필요.
6. **배열은 `Arrays.asList(arr)` 주의**: `int[]`에는 안 됩니다(`Integer[]`만). 그냥 for문으로 옮기는 게 안전합니다.

## 4. 왜 해시인가 (시간복잡도)

| 연산 | ArrayList | HashSet / HashMap |
|---|---|---|
| 맨 뒤 추가 | O(1) | O(1) |
| 중간 삽입/삭제 | **O(n)** | - |
| 값 존재 확인 `contains` | **O(n)** | **O(1)** 평균 |
| 인덱스로 읽기 | O(1) | - |

→ **"이미 나왔는지 / 몇 번 나왔는지"를 물으면 해시.**
Day 1의 `TimeComplexity.java`에서 O(n²)였던 "합이 target인 두 수 찾기"가 HashSet으로 **O(n)** 이 됩니다 (`HashBasics.java`의 `hasPairWithSum`).

### 해시가 동작하는 원리 (책 Ch12 요약)
- 키 → **해시 함수** → 배열 인덱스 → 거기에 저장
- 서로 다른 키가 같은 인덱스로 가면 **충돌**. 자바는 **체이닝**(한 칸에 리스트로 이어붙임)으로 해결
- 그래서 평균 O(1), 최악(전부 충돌) O(n). 코테에서는 평균 O(1)로 생각하면 됩니다.
- 직접 만든 클래스를 키로 쓰려면 `equals()`와 `hashCode()`를 같이 구현해야 함 (코테에서는 좌표를 `r * N + c`나 `"r,c"` 문자열로 바꿔 쓰는 편이 쉬움)

## 5. 자주 나오는 패턴

```java
// (1) 빈도수 세기
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) freq.merge(c, 1, Integer::sum);

// (2) 중복 제거
Set<Integer> uniq = new HashSet<>();
for (int x : arr) uniq.add(x);        // uniq.size() 가 서로 다른 값의 개수

// (3) 그룹핑
Map<String, List<String>> groups = new HashMap<>();
groups.computeIfAbsent(key, k -> new ArrayList<>()).add(value);

// (4) 인덱스 저장해서 O(1) 조회 (이름 -> 순위 같은 것)
Map<String, Integer> pos = new HashMap<>();
for (int i = 0; i < names.length; i++) pos.put(names[i], i);
```

## 6. 오늘 한 줄 정리 (직접 채우기)

- 오늘 새로 알게 된 것:
- 헷갈린 것:
- 막혔던 문제:
