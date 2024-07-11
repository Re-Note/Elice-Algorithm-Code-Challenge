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