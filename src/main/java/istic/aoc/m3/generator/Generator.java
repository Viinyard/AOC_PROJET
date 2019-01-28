package istic.aoc.m3.generator;

import istic.aoc.m3.diffusion.DiffusionStrategy;

/**
 * @author VinYarD
 * created : 22/01/2019, 11:04
 */


public interface Generator {
	
	long getValue();
	
	void setDiffusionStrategy(DiffusionStrategy strategy);
	
	void setValue(long value);
}
