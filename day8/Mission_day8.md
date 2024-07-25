# 앨리스 알고리즘 코드 챌린지 8일차

![image](https://imgur.com/ucRQM1q.png)

# 강림제

**시간 제한**: 1초

코더 랜드에는 "엘리스"라는 신을 믿는 종교가 있습니다. 

신실한 신도 체셔는 엘리스를 지상에 강림할 수 있게 해주는 강림제를 열려고 합니다.

체셔는 신도들에게 초대장을 돌리고, 초대장을 받은 신도들은 모두 자신이 언제 와서 언제 떠날 것인지 답변을 해주었습니다.

강림제가 시작되고 강림제에 참여 중인 사람들은 모두 기도를 시작합니다.

기도 중인 신도가 T명 이상이 되는 순간 엘리스가 지상에 강림하고 T명 미만이 되면 다시 사라진다고 합니다.

강림제를 담당하는 체셔는 기도 중인 신도가 T명 미만이 되면 엘리스가 강림하지 못한다는 것을 알고 급하게 자신의 친구 K명을 부르려고 합니다.

하지만 체셔의 친구들은 부끄러움이 많아서 체셔의 친구들을 제외한 신도가 T명 이상이 되는 순간 다 같이 강림제에서 나가 버리고 돌아오지 않는다고 합니다.

또한 기도 중인 인원이 T명 이상이면 체셔의 친구들은 강림제에 들어가지 않습니다.

단, 아직 들어가지 않은 체셔의 친구들은 이후 기도 인원이 T명 미만이 되면 강림제에 들어갈 수 있습니다.

체셔는 각각의 친구들을 적절한 시각에 강림제에 투입해 최대한 오랫동안 엘리스를 지상에 강림시키려고 합니다.

꼭 K명 모두 투입할 필요는 없습니다.

체셔는 엘리스를 얼마나 오랫동안 지상에 머물게 할 수 있는지 구하는 프로그램을 작성하세요.

## 지시사항

### 입력

첫째 줄에 강림제가 진행되는 시간 N, 강림제에 초대한 신도의 수 M, 체셔의 친구 수 K, 엘리스가 강림하기 위해 필요한 최소의 기도 인원 T를 입력합니다.

모든 입력은 자연수입니다.

- 1 ≤ N, M, K ≤ 300

- 1 ≤ T ≤ M

둘째 줄부터 M개의 줄에 걸쳐 줄마다 aᵢ, bᵢ를 입력합니다. i번째 사람은 시각 aᵢ에 기도에 참여하고, 시각 bᵢ에 강림제를 떠납니다.

- 1 ≤ aᵢ ≤ N

- aᵢ < bᵢ ≤ N + 1

강림제가 시작되는 시각은 1을 기준으로 합니다.

### 출력

체셔의 친구들을 강림제에 투입했을 때 엘리스가 지상에 강림할 수 있는 최대 시간을 출력합니다.

### 입력 예시

```
11 3 2 2
3 12
5 10
7 8
```

### 출력 예시

```
9
```

## 제출 코드

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 총 일수, M: 이벤트 수, K: 친구 수, T: 최소 인원수
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int T = scanner.nextInt();

        // startCount와 endCount 배열을 사용하여 각 날에 이벤트로 시작하고 끝나는 사람 수를 기록
        int[] startCount = new int[N + 1];
        int[] endCount = new int[N + 2];

        // M개의 이벤트 입력을 처리하여 시작일과 종료일에 따라 사람 수를 기록
        for (int i = 0; i < M; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            startCount[start] += 1;
            endCount[end] += 1;
        }

        // 현재 사람 수와 사용한 친구 수를 추적하기 위한 변수
        int currentPeople = 0;
        int usedFriends = 0;
        int answer = N;  // 기준을 만족하는 날의 수 (초기값은 N)

        // 각 날을 순회하면서 사람 수를 갱신하고 기준을 만족하는지 확인
        for (int day = 1; day <= N; day++) {
            // startCount[day] 사람 추가하고 endCount[day] 사람 제거
            currentPeople = currentPeople + startCount[day] - endCount[day];

            // 현재 사람들이 최소 인원수 T 이상인 경우
            if (currentPeople - usedFriends >= T) {
                // 친구를 사용한 사람 수를 빼고 다시 0으로 설정
                currentPeople = currentPeople - usedFriends;
                usedFriends = 0;
            }
            // 현재 사람이 T명 미만인 경우
            else if (currentPeople < T) {
                int needed = T - currentPeople;  // 필요한 추가 사람 수

                // 남은 친구 수로 필요한 사람 수를 채울 수 없는 경우
                if (K < needed) {
                    answer--;  // 해당 날은 기준을 만족하지 못하므로 카운트를 줄임
                    continue;
                }

                // 남은 친구 수로 필요한 사람 수를 채울 수 있는 경우
                K = K - needed;  // 남은 친구 수를 갱신
                usedFriends = usedFriends + needed;  // 사용한 친구 수를 갱신
                currentPeople = T;  // 현재 사람 수를 최소 인원수 T로 설정
            }
        }

        // 기준을 만족하는 날의 수 출력
        System.out.println(answer);
    }
}
```

## 제출 결과

![image](https://imgur.com/EPb2X6A.png)

## 정답 코드

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][][] dp = new int[301][301][301];
    static int[] need = new int[301];

    // dp[i][j][k]는 i번째 시간에 친구 j명이 투입되고
    // 친구 k명이 남아있을 때 현재 + 앞으로 가능한 최대 강림 시간
    static int makeDP(int x, int y, int z) {
        if (dp[x][y][z] != -1) {
            return dp[x][y][z];
        }
        if (need[x] > 0 && need[x + 1] > 0) {
            dp[x][y][z] = makeDP(x + 1, y, z);
        } else if (need[x] > 0 && need[x + 1] <= 0) {
            dp[x][y][z] = makeDP(x + 1, 0, z);
        } else if (need[x] <= 0 && need[x + 1] > 0) {
            for (int i = 0; i <= z; i++) {
                dp[x][y][z] = Math.max(dp[x][y][z], makeDP(x + 1, i, z - i));
            }
        } else if (need[x] <= 0 && need[x + 1] <= 0) {
            dp[x][y][z] = makeDP(x + 1, y, z);
        }
        if (y >= need[x]) {
            dp[x][y][z] += 1;
        }
        return dp[x][y][z];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int T = sc.nextInt();

        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }
        Arrays.fill(need, T);
        need[0] = 0;

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            for (int j = a; j < b; j++) {
                need[j]--;
            }
        }

        for (int i = 0; i <= 300; i++) {
            for (int j = 0; j <= 300; j++) {
                if (i >= need[N]) {
                    dp[N][i][j] = 1;
                } else {
                    dp[N][i][j] = 0;
                }
            }
        }

        System.out.println(makeDP(0, 0, K) - 1);
    }
}
```
