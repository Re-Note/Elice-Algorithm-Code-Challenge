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