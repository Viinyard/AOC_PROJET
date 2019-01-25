package istic.aoc.m3;

import istic.aoc.m3.generator.Generator;

/**
 * @author VinYarD
 * created : 22/01/2019, 14:18
 */


public class RunnableGenerator implements Runnable {
	
	private boolean isRunning = true;
	
	private Generator generator;
	
	private long value = 0;
	
	public RunnableGenerator(Generator generator) {
		this.generator = generator;
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
		while(this.isRunning) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.generator.setValue(this.value++);
		}
		this.isRunning = true;
	}
}
