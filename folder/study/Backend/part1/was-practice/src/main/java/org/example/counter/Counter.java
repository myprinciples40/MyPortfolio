package org.example.counter;

// 싱글톤 객체에서 상태를 유지하게 설계하면 발생하는 문제 증명 코드
public class Counter implements Runnable {
    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    @Override
    public void run() {
        // 동기화 처리로 Thread가 안전하지 않은 문제를 해결할 수 있다.
        synchronized (this) {
            this.increment();
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue());
            this.decrement();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue());
        }
    }
}


