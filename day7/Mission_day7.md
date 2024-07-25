# 앨리스 알고리즘 코드 챌린지 7일차

![image](https://imgur.com/ucRQM1q.png)

# 계기판 조작하기

**시간 제한**: 1초

엘리스는 악질 중고차 딜러인 체셔를 싫어해 체셔를 골탕 먹이려고 합니다.

엘리스는 순식간에 자동차 주행거리 계기판을 조작할 수 있는 기술을 가지고 있습니다.

엘리스는 차를 구경하겠다고 체셔에게 부탁한 뒤 구경하는 시간 동안 계기판의 주행거리를 더 크게 조작해 체셔가 당황하게 만들 예정입니다.

원래 자동차의 주행거리가 N킬로미터면 여러분은 체셔에게 들키지 않도록 주행거리를 N보다 크면서, 가장 작은 수로 늘려놓으려고 합니다.

이때, 조작한 값은 서로 다른 K개의 숫자로 이루어져야 합니다.

예를 들어, **100000**이란 수는 **1**과 **0**으로 이루어져 있으므로, 2개의 숫자로 이루어진 수입니다.

N과 K를 줬을 때 조작할 수 있는 주행거리의 최솟값을 출력하는 프로그램을 작성하세요.

## 지시사항

### 입력

첫 번째 줄에 자연수 N과 K를 입력합니다.

- 1 ≤ N ≤10⁷

- 1 ≤ K ≤ 10

주행거리를 조작한 값이 전과 비교해 더 큰 경우만 입력합니다.

### 출력

첫 번째 줄에 조작할 수 있는 주행거리의 최솟값을 출력합니다.

### 입력 예시

```
100000 3
```

### 출력 예시

```
100002
```

## 제출 코드

```java
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
```

## 제출 결과

![image](https://imgur.com/byGAZtM.png)

## 정답 코드

```java
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String N = scanner.next();
        int K = scanner.nextInt();
        scanner.close();
        
        boolean[] digit = new boolean[10];
        int cnt = 0;
        
        if (K == 10) {
            System.out.println("1023456789");
        } else if (K == 9) {
            System.out.println("102345678");
        } else {
            while (cnt != K) {
                Arrays.fill(digit, false);
                cnt = 0;
                N = Integer.toString(Integer.parseInt(N) + 1);
                for (int i = 0; i < N.length(); i++) {
                    digit[N.charAt(i) - '0'] = true;
                }
                for (int i = 0; i < 10; i++) {
                    if (digit[i]) {
                        cnt++;
                    }
                }
            }
            System.out.println(N);
        }
    }
}
```
