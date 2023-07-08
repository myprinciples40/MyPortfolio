package org.example.counter;

// 싱글톤 객체에서 상태를 유지하게 설계하면 발생하는 문제 증명 코드
// Race Condition이란 여러 프로세스 또는 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태.
// 동기화 처리로 Thread가 안전하지 않은 문제를 해결할 수 있다. (되도록이면 서블릿 객체를 상태를 유지(stateful)하게 설계하지 말 것.)
public class RaceConditionDemo {
    public static void main(String[] args) {
        // 싱글톤 객체 생성
        Counter counter = new Counter();
        // 멀티 스레드 환경을 공유하면 우리가 원하지 않는 결과가 나올 수 있다.
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
