---
title: "앨리스 알고리즘 코드 챌린지 3일차"
categories: "Extern_Activity"
tag: [Extern_Activity]
toc: true
---

# 앨리스 알고리즘 코드 챌린지 3일차

![image](https://imgur.com/ucRQM1q.png)

# 문자열 압축 해제

**시간 제한**: 1초

엘리스 토끼는 문자열을 직접 압축 해제하려고 합니다.

압축되지 않은 문자열 S가 주어졌을 때, 이 문자열 중 어떤 부분 문자열은 K(Q)와 같이 압축할 수 있습니다.

이것은 Q라는 문자열이 K 번 반복된다는 뜻입니다. K는 한 자릿수의 정수이고, Q는 0자리 이상의 문자열입니다.

예를 들면, 53(8)은 다음과 같이 압축을 해제할 수 있습니다.

53(8) = 5 + 3(8) = 5 + 888 = 5888

압축된 문자열이 주어졌을 때, 이 문자열을 다시 압축을 푸는 프로그램을 작성하세요.

## 지시사항

### 입력

첫째 줄에 압축된 문자열 S를 입력합니다.

S의 길이는 최대 50입니다.

문자열은 ( , ) , 숫자로만 구성되어 있습니다.

### 출력

압축되지 않은 문자열의 길이를 출력합니다.

### 입력 예시

```
11(18(72(7)))
```

### 출력 예시

```
26
```

## 제출 코드

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(decompLen(s));
    }
    
    public static int decompLen(String s) {
        Stack<Integer> repeat = new Stack<>();
        Stack<Integer> len = new Stack<>();
        int curLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                // 다음 문자가 '(' 인지 확인
                if (i + 1 < s.length() && s.charAt(i + 1) == '(') {
                    repeat.push(num);
                } else {
                    curLen++;
                }
            } else if (ch == '(') {
                len.push(curLen);
                curLen = 0;
            } else if (ch == ')') {
                int cnt = repeat.pop();
                curLen = len.pop() + curLen * cnt;
            }
        }
        
        return curLen;
    }
}
```

## 제출 결과

![image](https://imgur.com/fdC9BR8.png)

## 정답 코드

```java
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        String string = new java.util.Scanner(System.in).nextLine();
        
        Stack<Character> stack = new Stack<>();
        int[] depthResult = new int[50];
        int depth = 0;
        
        for (char ch : string.toCharArray()) {
            if (ch != ')') {
                if (ch == '(') {
                    depth += 1;
                    depthResult[depth] = 0;
                }
                stack.push(ch);
            } else {
                for (int i = stack.size() - 1; i >= 0; i--) {
                    if (stack.get(i) == '(') {
                        int num = depthResult[depth];
                        for (int j = i + 1; j < stack.size(); j++) {
                            num += 1;
                        }
                        depth -= 1;
                        depthResult[depth] += num * Character.getNumericValue(stack.get(i - 1));
                        while (stack.size() > i - 1) {
                            stack.pop();
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(depthResult[0] + stack.size());
    }
}
```
