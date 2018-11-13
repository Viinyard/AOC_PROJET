package istic.aoc.m3.active;

import istic.aoc.m3.generator.Generator;

/**
 * @author VinYarD
 * created : 17/10/2018, 14:41
 */


public class Canal {
	
	private long time;
	
	public Canal(long time) {
	
	}
	
	public void update(Generator generator) {
		try {
			Thread.sleep(time);
			//generator.update();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
