import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준(BOJ) 스타일 입출력 템플릿. 프로그래머스는 필요 없지만
 * 대기업 코테(삼성 등)는 표준 입출력을 쓰는 곳도 있으니 익혀두세요.
 * 백준 제출 시 클래스 이름은 반드시 Main 이어야 합니다.
 *
 * 실행 예: (입력 파일 만들어서) java BojTemplate.java < input.txt
 * 입력 예:
 *   3
 *   10 20 30
 */
public class BojTemplate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());          // 한 줄에 정수 하나
        StringTokenizer st = new StringTokenizer(br.readLine());  // 공백으로 구분된 여러 값
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
        }
        sb.append(sum).append('\n');

        System.out.print(sb);      // 출력은 모아서 한 번에
    }
}
