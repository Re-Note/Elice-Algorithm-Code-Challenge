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