---
title: "앨리스 알고리즘 코드 챌린지 4일차"
categories: "Extern_Activity"
tag: [Extern_Activity]
toc: true
---

# 앨리스 알고리즘 코드 챌린지 4일차

![image](https://imgur.com/ucRQM1q.png)

# 트리 위의 게임

**시간 제한**: 1초

정점 N개의 트리에서 두 사람이 게임을 진행하려 한다.

각 정점은 1번부터 N번 까지 번호가 매겨져 있고 루트노드는 1번 노드이다.

게임은 서로 턴을 번갈아 가며 진행되고 트리 위에 놓을 수 있는 말과 함께 진행된다.

두 사람의 점수는 모두 0점으로 시작한다.

각 턴마다 두 사람은 다음과 같은 작업을 반복한다.

- 현재 말이 놓여 있는 정점의 번호만큼 자신의 점수에 더한다.

- 현재 말이 놓여 있는 정점의 자식 정점이 없다면 그대로 게임을 종료한다.

    자식 정점이 존재한다면 자식 정점 중 원하는 자식 정점으로 말을 옮긴다.

게임이 종료되었을 때 선공의 점수가 후공의 점수보다 높거나 같다면 선공이 승리하고 아니라면 후공이 승리한다.

두 사람이 최적으로 플레이할 때, 처음 말이 놓여져 있는 정점의 번호에 따라 선공이 이기는지 후공이 이기는지 구해보자.

## 지시사항

### 입력

첫째 줄에 정점의 수 N이 주어진다.

- 1 ≤ N ≤ 100000

둘째 줄부터 N−1개의 줄에 간선을 나타내는 정수 u,v가 주어진다.

- 1 ≤ u,v ≤ N

이는 u번 정점과 v번 정점을 잇는 간선이 존재한다는 뜻이다.

### 출력

N개의 줄에 걸쳐 정답을 출력한다.

i번째 줄에는 말의 시작위치가 i번 정점일 때의 결과를 출력한다.

선공이 이긴다면 1을 후공이 이긴다면 0을 출력한다.

### 입력 예시 1

```
5
1 3
2 1
3 4
5 1
```

### 출력 예시 1

```
1
1
0
1
1
```

### 입력 예시 2

```
6
1 3
1 2
3 5
3 6
2 4
```

### 출력 예시 2

```
1
0
0
1
1
1
```

## 제출 코드

```java
import java.util.*;

class Main {

    // 노드 클래스 정의
    static class Node {
        int index;          // 노드의 인덱스
        List<Integer> children;  // 자식 노드들의 리스트

        Node(int index) {
            this.index = index;
            this.children = new ArrayList<>();
        }
    }

    static Node[] tree;   // 트리를 저장할 배열
    static int[] dp;      // 각 노드에서의 최종 결과를 저장할 배열

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  // 노드의 개수 입력
        tree = new Node[N + 1];     // 1부터 N까지의 노드를 저장할 배열 생성
        dp = new int[N + 1];        // 각 노드의 결과를 저장할 배열 생성

        // 각 노드 객체 생성
        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(i);
        }

        // 트리 구성 (간선 입력)
        for (int i = 0; i < N - 1; i++) {
            int u = scanner.nextInt();  // 부모 노드
            int v = scanner.nextInt();  // 자식 노드
            tree[u].children.add(v);    // 부모 노드의 자식 리스트에 자식 노드 추가
        }

        // 게임 시뮬레이션 시작 (1번 노드에서 시작하고, 첫 번째 플레이어 차례)
        simulateGame(1, true);

        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dp[i] >= 0) {
                sb.append("1\n");  // dp[i]가 0 이상일 경우 첫 번째 플레이어가 이김
            } else {
                sb.append("0\n");  // dp[i]가 0 미만일 경우 두 번째 플레이어가 이김
            }
        }
        System.out.print(sb.toString());  // 결과 출력
    }

    // 게임 시뮬레이션 메서드
    static void simulateGame(int node, boolean isFirstPlayerTurn) {
        int sum = 0;        // 해당 노드의 모든 자식 노드들의 결과의 합
        boolean isChild = false;  // 자식 노드가 있는지 여부

        // 모든 자식 노드에 대해 게임 시뮬레이션을 재귀적으로 진행
        for (int child : tree[node].children) {
            isChild = true;
            simulateGame(child, !isFirstPlayerTurn);  // 플레이어 차례를 반대로 넘겨줌
            sum += dp[child];  // 자식 노드의 결과를 합산
        }

        // 자식 노드가 없는 경우 (리프 노드)
        if (!isChild) {
            dp[node] = node;  // 해당 노드의 결과는 자신의 인덱스로 설정
        } else {
            // 자식 노드가 있는 경우
            if (isFirstPlayerTurn) {
                dp[node] = Math.max(sum + node, -sum - node);  // 첫 번째 플레이어 차례일 때 최대값 선택
            } else {
                dp[node] = Math.min(sum + node, -sum - node);  // 두 번째 플레이어 차례일 때 최소값 선택
            }
        }
    }
}
```

## 제출 결과

![image](https://imgur.com/fXkMwRL.png)

## 정답 코드

```java
import java.util.*;

class Main {
    static long n, a, b;
    static ArrayList<Long>[] v = new ArrayList[100050];
    static long[] dp = new long[100050];
    static final long inf = (long)1e12;

    static void dfs(long x, long par) {
        long ret = inf;
        for (Long nxt : v[(int)x]) {
            if (nxt == par) continue;
            dfs(nxt, x);
            ret = Math.min(ret, dp[nxt.intValue()]);
        }
        if (ret == inf) ret = 0;
        dp[(int)x] = x - ret;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);  
        
        n=scanner.nextLong();
        
        for(int i=0;i<100050;i++)
            v[i]=new ArrayList<>();
            
        for(int i=1;i<n;i++){
            a=scanner.nextLong();
            b=scanner.nextLong();
            
            v[(int)a].add(b);
            v[(int)b].add(a);
        }
        
       dfs(1,0);
       
       for(int i=1;i<=n;i++){
           if(dp[i]>=0)
               System.out.println("1");
           else
               System.out.println("0");
       }
   } 
}
```
