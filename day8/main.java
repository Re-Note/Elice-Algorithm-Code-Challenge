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