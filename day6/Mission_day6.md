# 앨리스 알고리즘 코드 챌린지 6일차

![image](https://imgur.com/ucRQM1q.png)

# 빨간 선과 파란 선

**제한 시간**: 1초

N개의 정점이 있다.

차례마다 다음을 반복해서 N개의 정점 사이에 간선을 연결하려고 한다.

- 먼저 2개의 서로 연결되지 않은 정점 u와 v를 고른다.

- 그 이후, u가 포함된 연결 요소의 모든 정점들 각각에서, v가 포함된 연결 요소의 모든 정점들 각각으로 간선을 추가한다.

  - 간선의 색은 빨간색 혹은 파란색이다.

k번째 차례에 사용할 색깔이 주어질 때, 정점을 골라서 얻을 수 있는 빨간 간선 개수의 최솟값을 구하여라.

## 지시사항

### 입력

첫 번째 줄에 정점의 수 N이 주어진다.

- 2 ≤ N ≤ 30

두 번째 줄에 각 차례에 사용할 색깔을 표시하는 N−1개의 수가 공백을 구분으로 주어진다.

숫자가 0이면 빨간 간선을, 1이면 파란 간선을 사용한다는 뜻이다.

입력되는 모든 수들은 정수이다.

### 출력

문제의 조건을 만족하면서 간선을 연결할 때, 얻을 수 있는 빨간 간선 개수의 최솟값을 출력한다.

### 입력 예시

```
5
1 1 0 1
```

### 출력 예시

```
1
```

순서대로 아래와 같이 간선을 연결할 수 있다.

(이미지 미공개)

## 제출 코드

```java
import java.util.*;

class Main {
    static int totalResult = 0; // 총 결과 값을 저장할 변수

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in); // 입력을 받기 위한 Scanner 객체
        PriorityQueue<Graph> minHeap = new PriorityQueue<>((a, b) -> a.vertices - b.vertices); // 정점 수 기준 오름차순 우선순위 큐
        PriorityQueue<Graph> maxHeap = new PriorityQueue<>((a, b) -> b.vertices - a.vertices); // 정점 수 기준 내림차순 우선순위 큐
        int numGraphs = inputScanner.nextInt(); // 그래프의 개수 입력 받기

        // 그래프 객체들을 생성하고 두 개의 우선순위 큐에 추가
        for (int i = 0; i < numGraphs; i++) {
            Graph graph = new Graph(1); // 각각의 그래프 객체를 생성하고 초기 정점 수는 1로 설정
            minHeap.add(graph); // 최소 힙에 추가
            maxHeap.add(graph); // 최대 힙에 추가
        }

        int[] colors = new int[numGraphs - 1]; // 색상 정보를 저장할 배열

        // 각 색상에 대한 정보 입력 받기
        for (int i = 0; i < numGraphs - 1; i++) {
            colors[i] = inputScanner.nextInt();
        }

        // 각 그래프 처리를 함수로 분리하여 호출
        for (int i = 0; i < numGraphs - 1; i++) {
            processGraphs(colors, minHeap, maxHeap, i); // 그래프 처리 함수 호출
        }

        System.out.println(totalResult); // 최종 결과 출력
    }

    // 그래프 처리 함수
    public static void processGraphs(int[] colors, PriorityQueue<Graph> minHeap, PriorityQueue<Graph> maxHeap, int i) {
        Graph firstGraph, secondGraph;

        if (colors[i] == 0) {
            // 색상이 빨강일 경우
            firstGraph = minHeap.poll(); // 정점 수가 가장 적은 그래프 가져오기
            secondGraph = minHeap.poll(); // 다음으로 정점 수가 적은 그래프 가져오기
            maxHeap.removeAll(Arrays.asList(firstGraph, secondGraph)); // 최대 힙에서 해당 그래프들 제거
            totalResult += (firstGraph.vertices * secondGraph.vertices); // 결과 값에 더하기
        } else {
            // 색상이 파랑일 경우
            firstGraph = maxHeap.poll(); // 정점 수가 가장 많은 그래프 가져오기
            secondGraph = maxHeap.poll(); // 다음으로 정점 수가 많은 그래프 가져오기
            minHeap.removeAll(Arrays.asList(firstGraph, secondGraph)); // 최소 힙에서 해당 그래프들 제거
        }

        // 두 그래프를 합치고 우선순위 큐 업데이트
        secondGraph.vertices += firstGraph.vertices; // 두 그래프의 정점 수 합치기
        minHeap.add(secondGraph); // 최소 힙에 합친 그래프 추가
        maxHeap.add(secondGraph); // 최대 힙에 합친 그래프 추가
    }

    // 그래프 클래스 정의
    public static class Graph {
        int vertices; // 정점 수를 나타내는 변수

        public Graph(int vertices) {
            this.vertices = vertices;
        }
    }        
}
```

## 제출 결과

![image](https://imgur.com/KnFN8nD.png)

## 정답 코드

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            a[i] = scanner.nextInt();
        }
        Map<List<Integer>, Integer> dp = new HashMap<>();
        dp.put(new ArrayList<>(Collections.nCopies(N, 1)), 0);
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<>(Collections.nCopies(N, 1)));
        while (!queue.isEmpty()) {
            List<Integer> v = queue.pollFirst();
            for (int i = 0; i < v.size(); i++) {
                for (int j = i + 1; j < v.size(); j++) {
                    List<Integer> u = new ArrayList<>();
                    for (int k = 0; k < v.size(); k++) {
                        if (k == i) {
                            u.add(v.get(i) + v.get(j));
                        } else if (k != j) {
                            u.add(v.get(k));
                        }
                    }
                    Collections.sort(u);
                    if (!dp.containsKey(u)) {
                        queue.add(u);
                        dp.put(u, dp.get(v) + (1 - a[N - v.size()]) * v.get(i) * v.get(j));
                    } else {
                        dp.put(u, Math.min(dp.get(u), dp.get(v) + (1 - a[N - v.size()]) * v.get(i) * v.get(j)));
                    }
                }
            }
        }
        System.out.println(dp.get(Collections.singletonList(N)));
    }
}
```
