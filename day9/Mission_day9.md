# 앨리스 알고리즘 코드 챌린지 9일차

![image](https://imgur.com/ucRQM1q.png)

# 격자 위의 ELICE

**시간 제한**: 7초

엘리스는 N×N 격자 모양의 미로에 갇혀버렸다!

가장 왼쪽 위 칸의 좌표는 (1,1)이고 가장 오른쪽 아래 칸의 좌표는(N,N)이다.

엘리스는 위대한 마법사이기 때문에 미로 위에 흩어져 있는 글자들을 순서대로 모아 단어 ELICE를 만든다면, 그 자리에서 즉시 순간이동 마법을 이용해 미로를 탈출할 수 있다고 한다.

엘리스는 현재 (1,1)에 위치해 있다.

모든 격자에는 양의 정수가 에 쓰여져 있다.

몇몇 칸에는 글자가 놓여 있을 수 있다.

엘리스가 있는 칸에 글자가 놓여 있는 경우, 원한다면 그 글자를 얻을 수 있다.

글자를 얻는다면 다시 이 칸에 도달해도 다시 한 번 글자를 얻을 수는 없다.

이렇게 모은 글자를 얻은 순서대로 이었을 때, 단어 ELICE가 된다면 순간이동 마법을 사용할 수 있다.

엘리스가 어떠한 격자 칸에서 다른 격자 칸으로 이동하고 싶다면, 상하좌우 한 방향을 골라 인접한 격자 칸으로 이동할 수 있다. (단 미로를 벗어날 수는 없다.)

엘리스가 어떤 칸에서 인접한 칸으로 이동할 때, 두 칸위에 쓰여 있는 정수의 합만큼의 시간이 걸린다.

예를 들어 아래 예제1과 같이 3이 쓰여 있는 (1,2)격자에서 4가 쓰여 있는 (1,3)격자로 이동하려면 7의 단위 시간이 필요하다.

미로에는 정확히 2개의 E와, 1개의 L, 1개의 I, 1개의 C문자가 존재한다.

엘리스가 순간이동을 마법을 사용해 미로를 탈출하는 최소 단위 시간을 알려주자.

## 지시사항

### 입력

첫째 줄에 정수 N이 주어진다.

- 3 ≤ N ≤ 1000

둘째 줄부터 N+1번째 줄까지 aᵢ,ⱼ가 주어진다.

i+1번째 줄에 들어오는 j번째 정수는 aᵢ,ⱼ이고, 격자 (i,j)에 쓰여있는 정수를 의미한다.

- 1 ≤ aᵢ,ⱼ ≤ 1000

N+2번째 줄부터 N+6번째 줄까지는 정수 r, c가 주어진다.

각 줄에 주어지는 정보는, 격자 (r,c)에 순서대로 글자 E, L, I, C, E가 놓여있음을 의미한다.

- 1 ≤ r, c ≤ N

N+2번째 줄에 입력된 위치의 E와 N+6번째 줄에 입력된 위치의 E는 프로그램 내에서 동일한 글자로 취급한다.

### 출력

첫째 줄에 엘리스가 미로를 탈출하는 최소 단위 시간을 출력한다.

### 입력 예시

```
3
2 3 4
1 4 3
1 1 1
1 1
2 1
3 1
3 2
3 3
```

### 출력 예시

```
9
```

## 제출 코드

```java
import java.util.*;

// Node 클래스 정의, 다익스트라 알고리즘에서 사용할 우선순위 큐의 요소
class Node implements Comparable<Node> {
    int x, y, cost;

    // 생성자
    Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    // 우선순위 큐에서 노드를 cost 기준으로 정렬하기 위해 compareTo 메서드 구현
    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}

public class Main {

    // 상하좌우 이동을 나타내는 방향 배열
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N;
    static int[][] grid;  // 격자 정보
    static int[][] letters;  // 각 문자의 좌표 정보
    static char[] sequence = {'E', 'L', 'I', 'C', 'E'};  // 방문할 문자 순서
    static int[][] dist;  // 거리 정보

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();  // 격자의 크기 입력
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();  // 격자의 비용 입력
            }
        }

        letters = new int[5][2];
        for (int i = 0; i < 5; i++) {
            letters[i][0] = sc.nextInt() - 1;  // 각 문자의 x 좌표 입력 (1을 빼서 0 기반으로 맞춤)
            letters[i][1] = sc.nextInt() - 1;  // 각 문자의 y 좌표 입력 (1을 빼서 0 기반으로 맞춤)
        }

        System.out.println(minimumEscapeTime());  // 최소 탈출 시간 계산 후 출력
    }

    // 'E', 'L', 'I', 'C', 'E' 순서로 각 좌표를 방문하면서 최소 비용을 계산하는 메서드
    private static int minimumEscapeTime() {
        int totalCost = 0;  // 총 비용
        int startX = 0, startY = 0;  // 시작점 (0, 0)

        for (int i = 0; i < 5; i++) {
            int targetX = letters[i][0];
            int targetY = letters[i][1];
            totalCost += dijkstra(startX, startY, targetX, targetY);  // 다익스트라 알고리즘으로 최소 비용 계산
            startX = targetX;
            startY = targetY;
        }

        return totalCost;  // 총 비용 반환
    }

    // 다익스트라 알고리즘 구현, (startX, startY)에서 (targetX, targetY)까지의 최소 비용 계산
    private static int dijkstra(int startX, int startY, int targetX, int targetY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);  // 거리 배열 초기화
        }

        dist[startX][startY] = 0;  // 시작점의 거리를 0으로 설정
        pq.offer(new Node(startX, startY, 0));  // 시작점을 우선순위 큐에 추가

        while (!pq.isEmpty()) {
            Node current = pq.poll();  // 우선순위 큐에서 가장 비용이 적은 노드를 꺼냄
            int x = current.x;
            int y = current.y;
            int cost = current.cost;

            // 목표 지점에 도달하면 현재 비용 반환
            if (x == targetX && y == targetY) {
                return cost;
            }

            // 현재 비용이 기록된 거리보다 크면 무시
            if (cost > dist[x][y]) continue;

            // 상하좌우로 이동
            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                // 격자의 범위를 벗어나지 않는 경우
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int newCost = cost + grid[x][y] + grid[nx][ny];  // 새로운 비용 계산
                    // 새로운 비용이 기록된 거리보다 작으면 업데이트하고 우선순위 큐에 추가
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.offer(new Node(nx, ny, newCost));
                    }
                }
            }
        }

        return Integer.MAX_VALUE;  // 이 코드에 도달하면 문제가 있는 것임 (입력 값이 유효하지 않음)
    }
}
```

## 제출 결과

![image](https://imgur.com/wg2qsP7.png)

## 정답 코드

```java
import java.util.*;
import java.io.*;
 
public class Main {
    static final int MAX = 2000100;
    static final long INF = (long) 1e18;
    static final int[] dirY = {-1, 0, 1, 0};
    static final int[] dirX = {0, 1, 0, -1};
    static int N;
    static long[] A = new long[MAX];
    static List<List<Pair>> v = new ArrayList<>();
    static long[][] dist = new long[MAX][5];
 
    static class Pair {
        int index;
        long cost;
        Pair(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
    }
 
    public static int get(int y, int x) {
        return y * N + x;
    }
 
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        N = sc.nextInt();
       
        for (int i = 0; i < MAX; i++) {
            v.add(new ArrayList<>());
        }
       
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int a = sc.nextInt();
                A[get(i, j)] = a;
            }
        }
 
        List<int[]> elice = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            elice.add(new int[]{y, x});
        }
 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int current = get(i, j);
                for (int k = 0; k < 4; k++) {
                    int y = i + dirY[k];
                    int x = j + dirX[k];
                    if (y < 1 || y > N || x < 1 || x > N) {
                        continue;
                    }
                    int next = get(y, x);
                    v.get(current).add(new Pair(next, A[current] + A[next]));
                }
            }
        }
 
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < MAX; j++) {
                dist[j][i] = INF;
            }
            int[] start = elice.get(i);
            int now = get(start[0], start[1]);
            dist[now][i] = 0;
 
            PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));
            pq.add(new Pair(now, 0));
 
            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                long curDist = p.cost;
                int curIndex = p.index;
 
                for (Pair next : v.get(curIndex)) {
                    if (dist[next.index][i] > curDist + next.cost) {
                        dist[next.index][i] = curDist + next.cost;
                        pq.add(new Pair(next.index, curDist + next.cost));
                    }
                }
            }
        }
 
        long answer1 = dist[get(1, 1)][0];
        for (int i = 1; i < 5; i++) {
            answer1 += dist[get(elice.get(i)[0], elice.get(i)[1])][i - 1];
        }
 
        long answer2 = dist[get(1, 1)][4];
        answer2 += dist[get(elice.get(1)[0], elice.get(1)[1])][4];
        answer2 += dist[get(elice.get(2)[0], elice.get(2)[1])][1];
        answer2 += dist[get(elice.get(3)[0], elice.get(3)[1])][2];
        answer2 += dist[get(elice.get(0)[0], elice.get(0)[1])][3];
 
        System.out.println(Math.min(answer1, answer2));
    }
 
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
 
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
```
