package com.company;

class Counter {
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized void increment(){
        count++;
    }
}
class PrimeChecker implements Runnable {
    int start;
    int end;
    int count;

    @Override
    public void run() {
        for (int i = start; i <= end; i++){
            if (CheckNumber(i)){
                count++;
            }
        }
    }
    public boolean CheckNumber(int x){
        if (x < 2 ){
            return false;
        }
        for (int i = 2; i < x; i++){
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }

    public int getCount() {
        return count;
    }

    public PrimeChecker(int start, int end) {
        this.start = start;
        this.end = end;
        this.count = 0;
    }
}
public class Main {

    public static void main(String[] args) throws InterruptedException {

        PrimeChecker primeChecker1 = new PrimeChecker(0, 250000);
        Thread thread1 = new Thread(primeChecker1);
        PrimeChecker primeChecker2 = new PrimeChecker(250001, 500000);
        Thread thread2 = new Thread(primeChecker2);

        Counter counter = new Counter(0);

        Runnable CheckNumbers = () -> {
            for (int i = 0; i < 1000; i++){
                counter.increment();
            }
        };

        PrimeChecker checknumber = new PrimeChecker(0, 250);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(primeChecker1.getCount() + primeChecker2.getCount());
    }
}
