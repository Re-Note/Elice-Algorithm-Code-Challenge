import java.util.*;

public class Main {

    // 숫자가 정확히 K개의 서로 다른 숫자를 포함하는지 확인하는 메서드
    public static boolean hasKDigits(long num, int k) {
        if (k > 10) {
            return false; // k가 10을 초과하면 조건을 만족할 수 없으므로 false 반환
        }

        int[] digitCount = new int[10]; // 각 자릿수별 숫자 등장 횟수를 저장할 배열

        // 숫자를 각 자릿수별로 분리하여 배열에 등장 횟수 기록
        while (num > 0) {
            int digit = (int) (num % 10); // num의 가장 낮은 자릿수 구하기
            digitCount[digit]++; // 해당 자릿수의 등장 횟수 증가
            num /= 10; // 다음 자릿수로 넘어가기
        }

        int differentDigits = 0; // 서로 다른 숫자의 개수를 세는 변수 초기화
        // 배열을 순회하며 등장 횟수가 1 이상인 숫자들의 개수를 센다
        for (int i = 0; i < digitCount.length; i++) {
            if (digitCount[i] > 0) {
                differentDigits++; // 등장 횟수가 1 이상인 경우, 서로 다른 숫자로 간주
            }
        }

        return differentDigits == k; // 서로 다른 숫자의 개수가 k와 일치하는지 반환
    }

    // 주어진 수 N보다 큰 수 중 K개의 서로 다른 숫자로 이루어진 최솟값 찾기
    public static long findMinDist(long n, int k) {
        long current = n + 1; // 주어진 수보다 큰 수부터 시작

        while (true) {
            if (hasKDigits(current, k)) {
                return current; // 현재 수가 조건을 만족하면 반환
            }
            current++; // 다음 수로 이동
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong(); // 입력 수 N
        int k = sc.nextInt(); // 서로 다른 숫자의 개수 K
        sc.close(); // 스캐너 닫기

        // 주어진 수 N보다 큰 수 중 K개의 서로 다른 숫자로 이루어진 최솟값 출력
        System.out.println(findMinDist(n, k));
    }
}