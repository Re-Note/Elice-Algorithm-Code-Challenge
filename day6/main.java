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