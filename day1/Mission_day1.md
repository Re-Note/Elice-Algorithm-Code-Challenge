---
title: "앨리스 알고리즘 코드 챌린지 5일차"
categories: "Extern_Activity"
tag: [Extern_Activity]
toc: true
---

# 앨리스 알고리즘 코드 챌린지 5일차

![image](https://imgur.com/ucRQM1q.png)

# 수열 복원

**시간 제한**: 1초

양의 정수로 이루어진 수열 a₁, a₂, ⋯, aₙ 이 있습니다.

- 1 ≤ aᵢ ≤ 10⁵
 
이 수열에서 각 원소를 선택하거나 선택하지 않음으로써 총 2ⁿ개의 부분 수열을 만들 수 있고, 만들어진 모든 부분 수열의 합인 2ⁿ개의 정수가 주어졌을 때, 원래의 수열 a₁, a₂, ⋯, aₙ을 구하는 프로그램을 작성하세요.

## 지시사항

### 입력

첫째 줄에 정수 n이 주어집니다.

- 1 ≤ n ≤ 15

둘째 줄에 이 수열에서 만들 수 있는 모든 부분 수열의 합인 2ⁿ개의 정수 s₁, s₂, ⋯, s2ⁿ이 주어집니다.

- 0 ≤ sᵢ ≤ n × 10⁵ 

### 출력

첫째 줄에 원래 수열의 원소를 오름차순으로 출력합니다.

### 입력 예시

```
3
1 4 7 3 0 6 5 2
```

### 출력 예시

```
1 2 4
```

## 제출 코드

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // n 입력 받기
        int n = Integer.parseInt(scanner.nextLine());

        // nums 입력 받기
        String[] numsStr = scanner.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < numsStr.length; i++) {
            nums.add(Integer.parseInt(numsStr[i]));
        }

        // 오름 차순 정렬
        Collections.sort(nums);

        List<Integer> answers = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        // 첫 번째 '원래 수열의 원소' 추가
        answers.add(nums.get(1));
        sums.add(0);
        sums.add(nums.get(1));

        nums = nums.subList(2, nums.size());

        // 원래 수열의 원소를 찾는 루프
        while (!nums.isEmpty()) {
            // 첫 번째 원소를 num에 저장
            int num = nums.get(0);
            answers.add(num);

            List<Integer> tempSums = new ArrayList<>();

            // 현재 sums에 있는 모든 합과 num을 더한 값을 tempSums에 추가
            for (int i = 0; i < sums.size(); i++) {
                int s = sums.get(i);
                tempSums.add(s + num);

                // nums에서 해당 값을 제거
                nums.remove(Integer.valueOf(s + num));
            }

            // tempSums를 sums에 추가
            sums.addAll(tempSums);

            // n개의 '원래 수열의 원소'를 찾았으면 종료
            if (answers.size() >= n) {
                break;
            }
        }

        for(int i = 0; i < answers.size(); i++){
            System.out.print(answers.get(i) + " ");
        }
    }
}
```

## 제출 결과

![image](https://imgur.com/3Sr1J8L.png)

## 정답 코드

```java
import java.util.*;

public class Main {

    static ArrayList<Long> res = new ArrayList<>();
    static ArrayList<Long> now = new ArrayList<>();

    public static void dfs(ArrayList<Long> res, int x, long sum_, ArrayList<Long> now, long m) {
        if (x == res.size()) {
            now.add(sum_ + m);
            return;
        }
        dfs(res, x + 1, sum_, now, m);
        dfs(res, x + 1, sum_ + res.get(x), now, m);
    }

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Long> v = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            long a = scanner.nextLong();
            v.add(a);
        }

        Collections.sort(v);

        for (int i = 1; i < v.size(); i++) {
            if (!now.contains(v.get(i))) {
                long m = v.get(i);
                dfs(res, 0, 0, now, m);
                res.add(v.get(i));
            }
            now.remove(v.get(i));
        }

        for (long nxt : res) {
            System.out.print(nxt + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve();
    }
}
```
