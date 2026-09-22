# Day 3. Stack / Queue / Deque

> 목표: **"최근 것 먼저(LIFO)"는 Stack, "먼저 온 것 먼저(FIFO)"는 Queue**라는 감각을 잡고, 실전에서는 둘 다 `Deque`로 처리하기

## 0. 오늘의 순서 (책 + 실습)

| 순서 | 할 일 | 자료 |
|---|---|---|
| 1 | 책 Ch6 스택 01·02 (개념 + 원리) | p.172~181, 구현 코드는 훑기 |
| 2 | 책 Ch6 05 스택 응용 (문자열 뒤집기, Postfix) | p.201~204 |
| 3 | 책 Ch7 큐 01·02 (개념 + 원리) | p.210~220, 구현 코드는 훑기 |
| 4 | VisuAlgo에서 Stack, Queue 애니메이션 한 번씩 보기 | visualgo.net |
| 5 | 예제 코드 실행 | `StackQueueBasics.java` |
| 6 | 문제 풀이 | `problems.md` |

## 1. 핵심 감각

- **Stack (LIFO, 후입선출)**: 마지막에 넣은 게 먼저 나옴. 접시 쌓기.
  → 괄호 짝 맞추기, 실행 취소(undo), 함수 호출 스택, 후위표기식 계산
- **Queue (FIFO, 선입선출)**: 먼저 넣은 게 먼저 나옴. 줄 서기.
  → BFS(Day 6), 프린터 대기열, 최근 요청 순서 처리

## 2. Java에서는 어떤 클래스를 쓰나 (중요)

Kotlin에 `ArrayDeque`가 있듯, Java도 **`Deque`(덱) 하나로 스택과 큐를 다 처리**하는 게 관례입니다.
`java.util.Stack`과 `java.util.Queue` 인터페이스는 존재하지만, 코테에서는 아래처럼 씁니다.

```java
Deque<Integer> stack = new ArrayDeque<>();   // 스택으로 쓸 때
Deque<Integer> queue = new ArrayDeque<>();   // 큐로 쓸 때
```

`java.util.Stack`을 안 쓰는 이유: 옛날 클래스라 느리고(synchronized), `Vector` 기반이라 요즘은 `Deque`를 권장합니다 (Java 공식 문서도 그렇게 안내).

## 3. Deque 메서드 대응표

| 동작 | 스택으로 쓸 때 | 큐로 쓸 때 |
|---|---|---|
| 넣기 | `push(x)` (앞에 추가) | `offer(x)` (뒤에 추가) |
| 꺼내기 | `pop()` (앞에서 제거+반환) | `poll()` (앞에서 제거+반환) |
| 보기만 | `peek()` (앞 원소, 제거 안 함) | `peek()` (앞 원소) |
| 비었는지 | `isEmpty()` | `isEmpty()` |

**Kotlin 대응**: `ArrayDeque`의 `addLast`/`removeLast`(스택), `addLast`/`removeFirst`(큐)와 이름은 다르지만 개념은 같습니다.

### 양쪽 다 쓸 수 있는 원본 메서드 (헷갈리면 이걸로 통일)

| 위치 | 추가 | 제거+반환 | 보기만 |
|---|---|---|---|
| 앞 | `addFirst(x)` | `pollFirst()` | `peekFirst()` |
| 뒤 | `addLast(x)` | `pollLast()` | `peekLast()` |

→ 스택 = 앞(또는 뒤) 한쪽만 사용, 큐 = 앞에서 빼고 뒤에 넣기.

## 4. 함정 4가지

1. **`Queue<Integer> q = new LinkedList<>();`도 됩니다.** 하지만 `ArrayDeque`가 더 빠르고 `null`을 못 넣어 실수를 막아줍니다. → **`ArrayDeque` 사용을 기본으로.**
2. **`pop()`/`poll()`을 빈 덱에 호출**: `pop()`은 예외(`NoSuchElementException`)를 던지고, `poll()`은 `null`을 반환합니다. 코테에서는 항상 `isEmpty()` 먼저 체크.
3. **`add(x)`와 `offer(x)`**: 둘 다 뒤에 추가하지만, 용량 제한이 없는 `ArrayDeque`에서는 사실상 동일합니다. `offer`가 관례.
4. **우선순위가 있는 큐가 필요하면 `PriorityQueue`** (Day 4). `Deque`는 순서만 보장, 정렬은 안 함.

## 5. 자주 나오는 패턴

```java
// (1) 괄호 짝 검사 - 스택의 가장 기본 문제
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (c == '(') stack.push(c);
    else {
        if (stack.isEmpty()) return false;   // 닫을 게 없는데 닫으려 함
        stack.pop();
    }
}
return stack.isEmpty();                       // 다 짝 맞았는지

// (2) 큐로 순서대로 처리 (시뮬레이션)
Deque<Integer> queue = new ArrayDeque<>();
for (int x : arr) queue.offer(x);
while (!queue.isEmpty()) {
    int cur = queue.poll();
    // 처리...
}

// (3) 스택으로 최근 값 추적 (다음 큰 수 찾기류)
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < arr.length; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        int idx = stack.pop();
        result[idx] = arr[i];       // idx 보다 뒤에 있는 첫 번째 큰 값
    }
    stack.push(i);
}
```

## 6. 오늘 한 줄 정리 (직접 채우기)

- 오늘 새로 알게 된 것:
- 헷갈린 것:
- 막혔던 문제:
