package com.epam.jmp.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Run {

	private static final Car[] cars = new Car[] {
		new Car("Mclaren", 5),
		new Car("Ferrari", 10),
		new Car("Porsche", 15),
		new Car("Lamborghini", 20),
	};
	
	public static void main(String[] args) throws InterruptedException {
		Lock cup = new ReentrantLock();
		CountDownLatch finish = new CountDownLatch(cars.length);
		Thread[] races = new Thread[cars.length];
		for(int i=0; i<cars.length; i++) {
			Car car = cars[i];
			races[i] = new Thread(cars[i]) {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					car.run();
					long end = System.currentTimeMillis();
					long time = end - start;
					printFinishTime(car, time);
					if (cup.tryLock()) {
						finish.countDown();
						try {
							finish.await();
							printWinner(car);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							cup.unlock();
						}
					} else {
						finish.countDown();
					}
				}
			};
		}
		for(Thread race : races) {
			race.start();
		}
	}
	
	private static void printWinner(Car winnerCar) {
		System.out.println("Winner is " + winnerCar.getName() + "!");
	}
	
	private static void printFinishTime(Car winnerCar, long millis) {
		double seconds = millis / 1000.0;
		System.out.println(String.format("%s finished at %.3f sec", winnerCar.getName(), seconds));
	}
	
}