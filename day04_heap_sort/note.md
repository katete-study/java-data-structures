# Day 4. PriorityQueue(힙) / Comparator / 커스텀 정렬

> 목표: **"가장 작은/큰 값을 매번 O(log n)에 꺼내는" 힙 감각**을 잡고, `Comparator`로 원하는 순서를 마음대로 만들기

## 0. 오늘의 순서 (책 + 실습)

| 순서 | 할 일 | 자료 |
|---|---|---|
| 1 | 책 Ch8 힙이란 (우선순위 큐와 힙, 완전 이진 트리, 힙의 조건) | p.244~250, 구현 코드는 훑기 |
| 2 | 책 Ch8 삽입·삭제 알고리즘 (개념만, 배열로 직접 구현하는 코드는 스킵) | p.251~256 |
| 3 | 책 Ch8 힙 수행 시간 (왜 O(log n)인지) | p.269 |
| 4 | 책 Ch9 정렬 개념 훑기 (선택·버블·삽입 — 코드보다 "왜 O(n²)인지"만) | p.272~282 |
| 5 | VisuAlgo에서 Heap 애니메이션 한 번 보기 | visualgo.net |
| 6 | 예제 코드 실행 | `HeapBasics.java` |
| 7 | 문제 풀이 | `problems.md` |

## 1. 핵심 감각

- **힙(Heap)**: "지금까지 넣은 것 중 최솟값(또는 최댓값)을 항상 root에 유지하는" 트리.
  삽입도 O(log n), 최솟값 꺼내기도 O(log n). 배열 전체를 매번 정렬하는 것보다 훨씬 빠름.
- **우선순위 큐(Priority Queue)**: 힙으로 구현된 큐. "먼저 넣은 것"이 아니라 **"우선순위가 높은 것"**이 먼저 나옴.
  → 작업 스케줄링, "매번 가장 작은/큰 값이 필요한" 문제에 사용.

### 왜 O(log n)인가 (책 Ch8 요약)
힙은 **완전 이진 트리**라서 높이가 log n입니다. 삽입/삭제 시 root부터 리프까지(또는 그 반대로) 한 경로만 타고 내려가며 비교·교환하므로, 트리 높이만큼인 O(log n)이 걸립니다. 배열 전체를 정렬하는 O(n log n)이나 매번 최솟값을 선형 탐색하는 O(n)보다 유리합니다.

## 2. Java에서는 `PriorityQueue`

```java
Queue<Integer> pq = new PriorityQueue<>();   // 기본: 최소 힙 (작은 값이 먼저 나옴)
pq.offer(5);
pq.offer(1);
pq.offer(3);
pq.poll();   // 1 (가장 작은 값)
pq.poll();   // 3
```

| 동작 | 메서드 |
|---|---|
| 넣기 | `offer(x)` |
| 최솟값(또는 최댓값) 꺼내며 제거 | `poll()` (비어있으면 `null`) |
| 보기만 | `peek()` |
| 크기 | `size()` |

## 3. 최대 힙 만들기 (자주 틀리는 부분)

`PriorityQueue`는 **기본이 최소 힙**입니다. 최대 힙이 필요하면 `Comparator`를 뒤집어야 합니다.

```java
// 방법 1: Collections.reverseOrder()
Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// 방법 2: 람다로 직접 비교 기준 작성 (a, b) -> b - a
Queue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);
```

**함정**: `(a, b) -> a - b`가 오름차순(최소 힙), `(a, b) -> b - a`가 내림차순(최대 힙)입니다.
값이 아주 크면(`Integer.MIN_VALUE` 근처) 뺄셈이 오버플로할 수 있어 `Integer.compare(a, b)`가 더 안전합니다.

## 4. Comparator — "정렬 기준을 내 맘대로"

`PriorityQueue`뿐 아니라 `Arrays.sort`, `Collections.sort`에도 똑같이 씁니다.

```java
// 문자열 길이 순 정렬
List<String> words = new ArrayList<>(List.of("banana", "kiwi", "fig"));
words.sort((a, b) -> a.length() - b.length());

// 여러 기준: 길이 같으면 사전순
words.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));

// 2차원 배열: 두 번째 값 기준 오름차순
int[][] arr = {{1, 5}, {2, 1}, {3, 3}};
Arrays.sort(arr, (a, b) -> a[1] - b[1]);
```

`Comparator.comparingInt(...)`, `.reversed()`, `.thenComparing(...)`를 조합하면 "1차 기준, 같으면 2차 기준" 같은 정렬을 깔끔하게 만들 수 있습니다.

## 5. 정렬 알고리즘 — 오늘은 "왜 O(n²)인지"만

책 Ch9의 선택/버블/삽입 정렬은 **직접 구현할 필요는 없고** (코테에서는 `Arrays.sort` 사용), 아래만 이해하면 됩니다.

- 셋 다 **이중 반복문**이라 O(n²)
- `Arrays.sort(int[])`는 O(n log n) (Dual-Pivot Quicksort)
- `Arrays.sort(Object[])`, `Collections.sort(List)`는 O(n log n) (Timsort, **안정 정렬**)

## 6. 자주 나오는 패턴

```java
// (1) "가장 작은/큰 K개" 문제 — 힙에 넣고 K번 꺼내기
Queue<Integer> pq = new PriorityQueue<>();
for (int v : arr) pq.offer(v);
int k = 3;
List<Integer> smallestK = new ArrayList<>();
for (int i = 0; i < k; i++) smallestK.add(pq.poll());

// (2) "두 값을 합쳐 없앤다" 류 — 최소 힙에서 2개씩 꺼내 합치고 다시 넣기 (더 맵게 패턴)
Queue<Integer> pq2 = new PriorityQueue<>();
for (int v : arr) pq2.offer(v);
while (pq2.size() > 1) {
    int a = pq2.poll();
    int b = pq2.poll();
    pq2.offer(a + b * 2);   // 예시 규칙
}

// (3) 커스텀 객체 정렬 (예: 이름-점수 쌍)
record Person(String name, int score) {}
List<Person> people = new ArrayList<>(List.of(new Person("a", 90), new Person("b", 70)));
people.sort((p1, p2) -> p2.score() - p1.score());   // 점수 내림차순
```

## 7. 오늘 한 줄 정리 (직접 채우기)

- 오늘 새로 알게 된 것:
- 헷갈린 것:
- 막혔던 문제:
