# 앨리스 알고리즘 코드 챌린지 10일차

![image](https://imgur.com/ucRQM1q.png)

# 계단 카드 뽑기

**시간 제한**: 5초

카드 주머니 N개가 차례로 있다.

이 중 i번째 카드 주머니에는 1이 적힌 카드, 2가 적힌 카드, ..., Aᵢ가 적힌 카드까지 총 Aᵢ장의 카드가 들어있다.

엘리스 토끼는 연속된 K개의 카드 주머니를 고르고, 각 카드 주머니에서 카드를 한 장씩 고른다.

이때, 엘리스 토끼가 고른 K장의 카드들에 1이 적힌 카드, 2가 적힌 카드, ..., K가 적힌 카드가 모두 하나씩 순서 상관없이 포함되어 있어야 한다.

엘리스 토끼가 이와 같이 카드를 고르는 방법이 존재하도록 하는 가장 큰 K의 값을 구해보자.

## 지시사항

### 입력

첫째 줄에 카드 주머니의 수 N이 주어진다.

- 1 ≤ N ≤ 50000

둘째 줄에 Aᵢ들이 공백으로 구분되어 주어진다.

- 1 ≤ Aᵢ ≤50000

### 출력

엘리스 토끼가 선택할 수 있는 가장 큰 K의 값을 출력한다.

### 입력 예시

```
5
1 1 2 5 3
```

### 출력 예시

```
4
```

2번째 주머니부터 5번째 주머니까지 연속으로 선택하면 1부터 4까지의 카드를 얻을 수 있습니다.

## 제출 코드

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Scanner를 통해 사용자로부터 입력을 받기 위해 초기화합니다.
        Scanner sc = new Scanner(System.in);

        // 첫 번째 입력: 배열의 크기 n을 읽어옵니다.
        int n = sc.nextInt();
        // 배열 a를 생성하여 크기 n으로 초기화합니다.
        int[] a = new int[n];
        // n개의 정수를 배열 a에 입력받습니다.
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        // Scanner를 닫습니다.
        sc.close();

        // 1부터 각 a[i]까지의 숫자를 포함하는 리스트를 생성합니다.
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 1; j <= a[i]; j++) {
                sub.add(j);
            }
            // 각 숫자의 리스트를 res에 추가합니다.
            res.add(sub);
        }

        // 최대 K값을 찾기 위한 초기화
        int maxK = 0;
        // K값을 1부터 n까지 시도하여 가장 큰 K를 찾습니다.
        for (int k = 1; k <= n; k++) {
            // 모든 k-조합을 저장할 리스트
            List<List<Integer>> combs = new ArrayList<>();
            // 주어진 리스트 res에서 k-조합을 생성합니다.
            genCombs(res, k, 0, new ArrayList<>(), combs);

            // 각 조합이 1부터 k까지의 모든 숫자를 포함하는지 확인합니다.
            boolean valid = false;
            for (List<Integer> comb : combs) {
                if (hasAll(comb, k)) {
                    valid = true;
                    break;
                }
            }

            // 유효한 조합이 있으면 최대 K값을 업데이트합니다.
            if (valid) {
                maxK = k;
            }
        }

        // 가장 큰 K값을 출력합니다.
        System.out.println(maxK);
    }

    // 주어진 리스트의 모든 k-조합을 생성하는 메소드
    private static void genCombs(List<List<Integer>> lists, int k, int start, List<Integer> cur, List<List<Integer>> res) {
        // 현재 조합의 크기가 k에 도달하면 결과 리스트에 추가합니다.
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 리스트의 모든 가능한 조합을 생성합니다.
        for (int i = start; i < lists.size(); i++) {
            for (Integer val : lists.get(i)) {
                cur.add(val);
                // 재귀 호출을 통해 다음 숫자를 추가합니다.
                genCombs(lists, k, i + 1, cur, res);
                // 마지막에 추가한 숫자를 제거하여 다른 조합을 시도합니다.
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 조합이 1부터 k까지의 모든 숫자를 포함하는지 확인하는 메소드
    private static boolean hasAll(List<Integer> comb, int k) {
        // 조합을 Set으로 변환하여 중복을 제거하고 빠르게 검색할 수 있게 합니다.
        Set<Integer> set = new HashSet<>(comb);
        // 1부터 k까지의 모든 숫자가 Set에 포함되어 있는지 확인합니다.
        for (int i = 1; i <= k; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
```

## 제출 결과

![image](https://imgur.com/lk4dhjV.png)

## 정답 코드

```java
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int leftIndex = 0;
        int maxLength = 0;
        int arraySize = Integer.parseInt(bufferedReader.readLine());
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] dataArray = new int[50001];
        int[] freqArray = new int[50001];
        for (int idx = 0; idx < arraySize; ++idx) {
            dataArray[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        for (int rightIndex = 0; rightIndex < arraySize; ++rightIndex) {
            updateFrequency(freqArray, 1, dataArray[rightIndex], 1);

            int currentLength = rightIndex - leftIndex + 1;
            while (!validate(freqArray, currentLength)) {
                updateFrequency(freqArray, 1, dataArray[leftIndex], -1);
                ++leftIndex;
                --currentLength;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        System.out.println(maxLength);
        bufferedReader.close();
    }

    private static void updateFrequency(int[] array, int start, int value, int increment) {
        for (int idx = start; idx <= value; ++idx) {
            array[idx] += increment;
        }
    }

    private static int computeSum(int[] array, int start, int end) {
        return java.util.Arrays.stream(array, start, end + 1).sum();
    }

    private static boolean validate(int[] freqArray, int currentLength) {
        for (int idx = 1; idx <= currentLength; ++idx) {
            if (freqArray[idx] < currentLength - idx + 1)
                return false;
        }
        return true;
    }
}
```
