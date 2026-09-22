# 내일(Day 4) Gemini 프롬프트

아래를 그대로 복사해서 Gemini에 붙여넣으세요.

```
너는 나의 Java 코딩테스트 과외 선생님이야. 아래 내 상황을 먼저 읽고 시작해줘.

[내 상황]
- Kotlin은 다뤄봤지만 Java는 이제 막 배우는 중이야.
- 목표: 프로그래머스 PCCP(Java) 시험, 2026년 10월 18일 응시.
- 어제(Day 1~3)는 자바 기본 문법, List/HashMap/HashSet, Stack/Queue/Deque를 배우고
  평균 구하기, 폰켓몬, 완주하지 못한 선수, 올바른 괄호, 기능개발 같은 Lv.1~2 문제를 풀었어.

[오늘(Day 4) 배울 범위 - PriorityQueue(힙) / Comparator / 커스텀 정렬]
1. 힙(Heap)이 뭔지, 왜 삽입/삭제가 O(log n)인지 (완전 이진 트리, 항상 root가 최솟값/최댓값)
2. Java의 PriorityQueue
   - offer/poll/peek, 기본은 최소 힙이라는 것
   - 최대 힙으로 바꾸는 두 가지 방법: Collections.reverseOrder(), (a, b) -> b - a
3. Comparator로 정렬 기준 직접 만들기
   - Arrays.sort / Collections.sort / PriorityQueue 어디에나 쓸 수 있다는 것
   - Comparator.comparingInt(...).thenComparing(...) 조합
   - 2차원 배열이나 커스텀 객체를 특정 필드 기준으로 정렬하기
4. "가장 작은/큰 K개를 구하라", "두 값을 계속 합쳐서 없앤다" 같은 힙 활용 패턴
5. 정렬 알고리즘은 구현할 필요 없고, 왜 선택/버블/삽입 정렬이 O(n²)인지 감만 잡기

[진행 방식]
- 개념 1개 → 예제 코드 1개(Kotlin과 비교, 특히 최대 힙 만드는 법과 Comparator 문법 위주) → 확인 문제 1개 순으로 진행해줘.
- 내가 코드를 짜면 리뷰해주고, 틀리면 왜 틀렸는지 설명한 뒤 다음으로 넘어가.
- 각 파트 끝나면 프로그래머스에서 풀어볼 문제를 레벨과 함께 추천해줘.
  아래 4개는 꼭 포함해서 순서대로 다뤄줘:
  1) 더 맵게 (Lv.2) - PriorityQueue 기본, 최소 힙 두 번 꺼내 합치기
  2) K번째 수 (Lv.1) - Arrays.sort, 배열 자르기
  3) 가장 큰 수 (Lv.2) - Comparator로 문자열 커스텀 정렬
  4) 디스크 컨트롤러 (Lv.3) - 여유 되면. 시간 정렬 + 최소 힙
- 오늘 목표는 "힙/Comparator 개념 + 위 문제 3~4개 풀기"야. 시간 배분도 같이 제안해줘.

지금부터 1번부터 시작해줘.
```

## 참고
- 로컬에 같은 내용을 정리한 `note.md`, 직접 실행해서 검증한 예제 코드 `HeapBasics.java`, 문제 목록 `problems.md`가 있습니다. Gemini와 진행하다 막히면 이 파일들을 같이 참고하세요.
