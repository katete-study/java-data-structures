# Day 1. 자바 기본 + 시간복잡도

> 목표: **Kotlin 머리로 Java 코드를 읽고 쓸 수 있게 되기**, 그리고 "이 코드가 N=10만에서 통과하나?"를 판단하기

## 0. 오늘의 순서 (책 + 실습)

| 순서 | 할 일 | 자료 |
|---|---|---|
| 1 | 책 Ch1 훑어읽기 (자료구조란, 알고리즘, ADT) | p.18~35 (가볍게, 20분) |
| 2 | 책 Ch4 **01 클래스, 02 자바 기초 문법** | p.64~78 (Kotlin과 다른 점 위주로) |
| 3 | 이 노트의 **Kotlin → Java 치트시트** 읽고 예제 코드 실행 | `KotlinToJava.java` |
| 4 | 책 Ch3 **알고리즘 복잡도 (Big-O)** | p.52~58 (수학적 정의 p.59~는 스킵) |
| 5 | `TimeComplexity.java` 돌려서 O(n) vs O(n²) 체감 | |
| 6 | 문제 풀이 | `problems.md` |

책 Ch4 중 **인터페이스(p.79), 제네릭(p.84), 패키지(p.87), 프로그램 수행(p.90), 개발 환경(p.97)** 은 오늘 스킵.
제네릭은 Day 2 시작할 때 `List<Integer>` 나올 때 맛만 봅니다.

## 1. Kotlin → Java 치트시트

### 1-1. 기본 문법

| 항목 | Kotlin | Java |
|---|---|---|
| 변수 | `val a = 1` / `var a = 1` | `final int a = 1;` / `int a = 1;` |
| 타입 | 추론(뒤에 씀) `a: Int` | **앞에 씀**, 문장 끝 `;` 필수 |
| 함수 | `fun solution(n: Int): Int` | `public int solution(int n)` |
| 문자열 템플릿 | `"a=$a"` | `"a=" + a` 또는 `String.format("a=%d", a)` |
| if 식 | `val x = if (c) 1 else 2` | `int x = c ? 1 : 2;` (삼항) |
| when | `when (x) { 1 -> ... }` | `switch (x) { case 1: ...; break; }` (**break 빼먹으면 아래로 흐름**) |
| 반복 | `for (i in 0 until n)` | `for (int i = 0; i < n; i++)` |
| 컬렉션 순회 | `for (x in list)` | `for (int x : list)` |
| null 안전 | `String?` | 없음. 모든 참조는 null 가능 (NPE 조심) |
| 출력 | `println(x)` | `System.out.println(x);` |

### 1-2. 배열과 길이 — 세 가지가 전부 다름 (가장 많이 틀리는 곳)

```java
int[] arr = new int[5];     // 0으로 초기화
int[] b = {1, 2, 3};
arr.length      // 배열: 필드 (괄호 없음)
str.length()    // String: 메서드
list.size()     // List, Map, Set 등: size()
```

Kotlin의 `IntArray(5)`, `intArrayOf(1,2,3)`, `arr.size`에 대응합니다.

### 1-3. 절대 틀리면 안 되는 함정 5가지

1. **문자열 비교는 `equals`**: Kotlin은 `==`가 값 비교인데, Java `==`는 **참조(주소) 비교**입니다.
   `s1.equals(s2)`를 쓰세요.
2. **제네릭에 기본형 불가**: `List<int>` ❌ → `List<Integer>` ✅.
   그리고 `Integer` 두 개를 `==`로 비교하면 -128~127 밖에서 false가 나옵니다. → `.equals()` 사용
3. **int 오버플로**: `int`는 약 ±21억. 합계나 곱셈은 `long`으로. `long x = 1L * a * b;`
4. **정수 나눗셈**: `1 / 2 == 0`, `(double) a / b`로 캐스팅해야 실수 나눗셈
5. **문자열 `+=` 반복은 O(n²)**: 반복문 안에서는 `StringBuilder`

### 1-4. 자주 쓰는 API (`ArraysAndStrings.java` 참고)

**문자열**
`s.length()`, `s.charAt(i)`, `s.substring(from, to)` (to 미포함), `s.indexOf("x")`,
`s.split(" ")`, `s.toCharArray()`, `s.toUpperCase()`, `s.equals(t)`, `s.isEmpty()`, `String.valueOf(x)`, `String.join(",", list)`

**변환**
`Integer.parseInt("12")`, `String.valueOf(12)`, `Integer.toBinaryString(n)`, `Integer.parseInt("101", 2)`,
`(char)('a' + 1)`, `c - '0'` (숫자 문자 → int), `Character.isDigit(c)`

**배열 (`java.util.Arrays`)**
`Arrays.sort(a)`, `Arrays.fill(a, v)`, `Arrays.toString(a)`, `Arrays.deepToString(a2d)`,
`Arrays.copyOf(a, n)`, `Arrays.copyOfRange(a, from, to)`, `Arrays.stream(a).sum()`

**수학**
`Math.max/min/abs`, `Math.pow(a, b)` (**double 반환**), `Math.sqrt`, `Integer.MAX_VALUE`, `Long.MAX_VALUE`

### 1-5. 프로그래머스 코드 형태

```java
class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        // ...
        return answer;
    }
}
```

- 입력은 `Scanner` 없이 **매개변수로 들어옵니다** (프로그래머스). 백준은 `BufferedReader` 필요 → `BojTemplate.java`
- 반환 타입이 `int[]`인데 `List<Integer>`로 풀었다면 변환이 필요합니다 (Day 2에서).

## 2. 시간복잡도 (Big-O) — 코테 관점 요약

책 Ch3의 수학 정의(Ω, Θ, 점근적 표기 정의)는 코테에서 쓸 일이 거의 없습니다.
**Big-O(최악의 경우)와 아래 표만 외우세요.**

### 2-1. 자주 나오는 복잡도

| 복잡도 | 예 |
|---|---|
| O(1) | 배열 인덱스 접근, HashMap get/put(평균) |
| O(log n) | 이분탐색, 힙 삽입/삭제, TreeMap 연산 |
| O(n) | 반복문 1번, 배열 전체 순회 |
| O(n log n) | 정렬 (`Arrays.sort`, `Collections.sort`) |
| O(n²) | 이중 반복문, 버블/선택/삽입 정렬 |
| O(2ⁿ), O(n!) | 부분집합 / 순열 완전탐색 |

### 2-2. 입력 크기 N으로 허용 복잡도 가늠하기 (1초 ≈ 1억 연산 기준)

| N | 통과 가능한 복잡도 |
|---|---|
| ≤ 10 | O(n!) 순열 완전탐색 |
| ≤ 20 | O(2ⁿ) 부분집합 |
| ≤ 500 | O(n³) |
| ≤ 5,000 | O(n²) |
| ≤ 10⁵~10⁶ | O(n log n) |
| ≤ 10⁷ | O(n) |
| 그 이상 | O(log n), O(1), 수식 |

**문제를 읽으면 N 범위부터 보세요.** "N ≤ 100,000인데 이중 for문" → 시간초과 → 다른 자료구조(해시, 정렬, 투포인터)가 필요하다는 신호입니다. 이게 자료구조를 배우는 이유입니다.

### 2-3. 복잡도 세는 법

- 반복문이 중첩되면 곱하고, 나란히 있으면 더한 뒤 가장 큰 항만 남깁니다.
  `for(n) { for(n) {} }  for(n) {}` → n² + n → **O(n²)**
- 상수와 계수는 무시: 3n + 5 → O(n)
- 반으로 줄어드는 반복(i *= 2, 이분탐색) → **O(log n)**

## 3. 오늘 한 줄 정리 (직접 채우기)

- 오늘 새로 알게 된 것:
- Kotlin과 달라서 헷갈렸던 것:
- 막혔던 문제:
