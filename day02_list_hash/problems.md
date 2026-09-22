# Day 2 문제 목록

> 규칙: Java로만. 30분 넘게 막히면 풀이 보고 **다음 날 다시**. 제목을 프로그래머스에서 검색하세요.

## A. 손으로 (책)

- [ ] 책 Ch5 연습문제 (p.166) 중 배열 리스트 관련 문제 1~2개만 (시간 없으면 스킵)
- [ ] 책 Ch12 연습문제 (p.441) 중 해시 함수 계산 문제 1개

## B. 프로그래머스 (해시 / 리스트)

| # | 문제 | 레벨 | 배우는 것 | 완료 |
|---|---|---|---|---|
| 1 | 폰켓몬 | Lv.1 | `HashSet`, 중복 제거, `Math.min` | [ ] |
| 2 | 완주하지 못한 선수 | Lv.1 | `HashMap` 빈도수, `getOrDefault`/`merge` | [ ] |
| 3 | 두 개 뽑아서 더하기 | Lv.1 | `HashSet` + **List → int[] 변환** + 정렬 | [ ] |
| 4 | 달리기 경주 | Lv.1 | `HashMap<String,Integer>`로 인덱스 O(1) 조회 (배열 swap) | [ ] |
| 5 | 신고 결과 받기 | Lv.1 | `Map<String, Set<String>>`, 중복 신고 처리 | [ ] |

여유가 되면 (선택):
- [ ] 의상 (Lv.2) — 카테고리별 개수 세고 경우의 수 곱하기, `Map`
- [ ] 전화번호 목록 (Lv.2) — 정렬 또는 `HashSet` + `substring`
- [ ] 성격 유형 검사하기 (Lv.1) — `HashMap<Character, Integer>` 점수 누적

## C. 오늘 체크리스트

- [ ] `List<int>`가 안 되는 이유와 대신 쓰는 타입은?
- [ ] `list.remove(1)`과 `list.remove(Integer.valueOf(1))`의 차이는?
- [ ] `map.get(k)`가 null일 수 있어서 `getOrDefault`를 쓰는 이유는?
- [ ] `list.contains`는 O(n), `set.contains`는 O(1)인 이유는? (해시 → 인덱스)
- [ ] 충돌이 났을 때 체이닝은 어떻게 해결하는가?
- [ ] "N=10만, 중복 여부 확인" 문제에서 이중 for문 대신 무엇을 쓸까?
