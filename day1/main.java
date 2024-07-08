import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        
        // 정수 N을 문자열로 변환하고 각 자릿수를 문자 배열로 저장
        char[] digits = String.valueOf(N).toCharArray();
        int length = digits.length;

        int i = length - 1;
        // 뒤에서부터 시작하여 첫 번째 감소하는 위치 찾기
        while (i > 0 && digits[i - 1] >= digits[i]) {
            i--;
        }

        // 만약 감소하는 위치가 없으면 (즉, 배열이 내림차순 정렬되어 있으면)
        // 배열을 오름차순으로 정렬하여 출력
        if (i == 0) {
            Arrays.sort(digits);
            System.out.println(new String(digits));
            return;
        }

        int j = length - 1;
        // 감소 위치의 이전 자리보다 큰 첫 번째 숫자 찾기
        while (digits[j] <= digits[i - 1]) {
            j--;
        }

        // 이 숫자와 감소 위치의 이전 자리 숫자를 교환
        swap(digits, i - 1, j);

        // 감소 위치 이후의 배열을 오름차순으로 정렬
        reverse(digits, i, length - 1);

        // 결과 출력
        System.out.println(new String(digits));
    }

    // 배열의 두 원소를 교환하는 메소드
    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 배열의 부분을 뒤집는 메소드
    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}