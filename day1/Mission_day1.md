---
title: "앨리스 알고리즘 코드 챌린지 1일차"
categories: "Extern_Activity"
tag: [Extern_Activity]
toc: true
---

# 앨리스 알고리즘 코드 챌린지 1일차

![image](https://imgur.com/ucRQM1q.png)

# 목표량

**시간 제한**: 1초

엘리스 토끼는 목표량을 정해 수학 문제를 열심히 풉니다. 목표량은 정수입니다.

내일 풀 수학 문제의 개수는 오늘 푼 문제 개수의 수와 숫자의 구성이 같으면서, 오늘 푼 문제 개수의 수보다 큰 수 중 가장 작은 수입니다.

예를 들어, 오늘 67문제를 풀었으면 다음 날 76문제를 풉니다.

오늘 푼 문제의 개수를 줬을 때 다음날 풀 문제의 개수를 출력하는 프로그램을 작성하세요.

## 지시사항

### 입력

첫 번째 줄에 오늘 푼 문제의 개수인 자연수 N을 입력합니다.

- 1 ≤ N ≤ 999999

정답이 반드시 있는 경우만 입력값으로 주어집니다.

### 출력

다음날 풀 문제의 개수를 출력합니다.

### 입력 예시

```
364
```

### 출력 예시

```
436
```

## 제출 코드
```java
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
```

## 제출 결과

![image](https://imgur.com/8TvtVNc.png)

## 정답 코드
```java
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        char[] charArray = s.toCharArray();
        
        if (nextPermutation(charArray)) {
            System.out.println(new String(charArray));
        } else {
            System.out.println(s);
        }
    }
    
    public static boolean nextPermutation(char[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }
        
        if (i < 0) {
            return false;
        }
        
        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }
        
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        
        reverse(array, i + 1, array.length - 1);
        return true;
    }
    
    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}
```
