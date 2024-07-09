---
title: "앨리스 알고리즘 코드 챌린지 2일차"
categories: "Extern_Activity"
tag: [Extern_Activity]
toc: true
---

# 앨리스 알고리즘 코드 챌린지 2일차

![image](https://imgur.com/ucRQM1q.png)

# 정리 정돈을 좋아하는 k씨

**시간 제한**: 1초

정리 정돈을 좋아하는 k씨의 본명은 아무도 모릅니다.

사람들은 k씨의 특이한 행동 2가지 때문에 그를 '정리 정돈을 좋아하는 k씨'라고 부릅니다.

그 두 가지 행동은 그가 숫자를 정리하는 일을 하면 아무 규칙없이 나열되어 있는 숫자중 범위를 정한 후 무조건 오름차순으로 정리한다는 것, 그리고 오름차순으로 정리된 숫자 중 k번째 숫자를 선택한다는 것입니다.

예를 들어 a={1,7,6,8,1,6,4,5}라는 수열이 있습니다.

정리정돈을 좋아하는 k씨는 범위를 2에서 5로 정하고, k를 2라고 정했습니다.

그러면 kₐ = {7,6,8,1}이 되고, 이것을 오름차순으로 정리를 하면 kₐ ={1,6,7,8}이 됩니다.

그리고 k씨는 2번째인 6을 선택합니다.

배열 a가 주어지고, k씨가 일을 한 횟수가 주어졌을 때, k씨가 고른 숫자를 출력하는 프로그램을 작성하세요.

## 지시사항

### 입력

첫째 줄에 배열의 크기인 정수 n과 k씨가 일한 횟수인 정수 m을 입력합니다.

- 1 ≤ n ≤ 10000

- 1 ≤ m ≤500

둘째 줄에는 배열에 포함된 정수를 순서대로 입력합니다. 각 정수는 절댓값이 200을 넘지 않는 정수입니다.

다음 줄 부터 m개 줄에 걸쳐 k씨가 고른 범위인 정수 i, j와 정수 k를 입력합니다.

- 1 ≤ i ≤ j ≤ n

### 출력

k씨가 일할 때마다 k씨가 선택한 숫자를 한 줄에 하나씩 출력합니다.

### 입력 예시

```
8 3
1 7 6 8 1 6 4 5
1 5 3
2 6 2
4 8 3
```

### 출력 예시

```
6
6
5
```

## 제출 코드

```java
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
```

## 제출 결과

![image](https://imgur.com/VSbKULG.png)

## 정답 코드

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(sc.nextInt());
        }
        for (int cnt = 0; cnt < m; cnt++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();
            List<Integer> part = new ArrayList<>(seq.subList(i - 1, j));
            Collections.sort(part);
            System.out.println(part.get(k - 1));
        }
        sc.close();
    }
}
```
