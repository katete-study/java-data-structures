# 학습 사이트 & 도구

## 문제 풀이 (우선순위 순)

| 사이트 | 용도 | 비고 |
|---|---|---|
| [프로그래머스](https://programmers.co.kr) | **메인.** PCCE/PCCP 응시 환경과 동일. Lv.1~3, 기출문제, 코딩 기초 트레이닝 | 입력은 `solution` 매개변수로 받음. 답 제출 후 다른 사람 풀이 볼 수 있음 |
| [백준 (BOJ)](https://www.acmicpc.net) | 표준 입출력 연습, 삼성 SW 역량테스트 유형 문제(시뮬레이션/BFS/DFS) 다수 | 자바 클래스 이름은 반드시 `Main`. 처음엔 **단계별로 풀어보기** 메뉴 추천 |
| [solved.ac](https://solved.ac) | 백준 난이도 티어(브론즈~) / 태그별 문제 검색 | 브론즈~실버 위주로 |
| SWEA (SW Expert Academy) | 삼성 지원 시 유형 연습 | 필요할 때만 |

## 자료구조 시각화 / 문법

| 사이트 | 용도 |
|---|---|
| [VisuAlgo](https://visualgo.net) | 스택, 큐, 힙, 트리, 정렬, 그래프 동작을 애니메이션으로 확인. **책을 읽으면서 같이 열어두기** |
| [점프 투 자바 (wikidocs)](https://wikidocs.net/book/31) | 무료 Java 문법. Kotlin 경험자는 필요한 장(배열, 문자열, 컬렉션)만 |
| [Java API 문서](https://docs.oracle.com/en/java/javase/21/docs/api/) | `ArrayList`, `HashMap` 등 메서드 확인 |

## 추천 사용법

1. 책으로 개념 → VisuAlgo로 동작 확인 → 프로그래머스로 문제 (하루 순서)
2. 프로그래머스 문제는 **Lv.1 → Lv.2** 순서로. 처음부터 Lv.2로 가지 마세요
3. 풀고 나서 **다른 사람의 Java 풀이**를 꼭 1~2개 봅니다 (API 활용을 배우는 가장 빠른 방법)
4. 한 번 틀린 문제는 정리했다가 이틀 뒤 다시 풀기

## Windows에서 한글 출력이 깨질 때

```bash
java -Dstdout.encoding=UTF-8 파일이름.java
```

또는 IntelliJ / VS Code에서 실행하면 해결됩니다. (콘솔 인코딩 문제이고 코드 문제가 아닙니다)
