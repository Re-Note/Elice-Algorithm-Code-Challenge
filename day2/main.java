import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 배열의 크기 n과 쿼리의 수 m 입력 받기
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 배열 a 입력 받기
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 쿼리 입력 받기
        int[][] queries = new int[m][3];
        for (int i = 0; i < m; i++) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
            queries[i][2] = scanner.nextInt();
        }

        // 각 쿼리 처리
        for (int q = 0; q < m; q++) {
            int i = queries[q][0] - 1; // 배열 인덱스로 변환
            int j = queries[q][1] - 1; // 배열 인덱스로 변환
            int k = queries[q][2];

            // 배열의 부분 배열 만들기
            int[] subarray = Arrays.copyOfRange(a, i, j + 1);

            // 부분 배열 정렬하기
            Arrays.sort(subarray);

            // k번째 숫자 출력하기 (인덱스는 0부터 시작하므로 k-1을 사용)
            System.out.println(subarray[k - 1]);
        }

        scanner.close();
    }
}