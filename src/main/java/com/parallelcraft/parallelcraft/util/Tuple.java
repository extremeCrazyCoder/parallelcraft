package com.parallelcraft.parallelcraft.util;

/**
 * An easy way to have to variables that belong together
 * 
 * @author extremeCrazyCoder
 */
public class Tuple<K, V> {
	private K arg1;
	private V arg2;
	
	public Tuple(K arg1, V arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public K getArg1() {
		return arg1;
	}

	public V getArg2() {
		return arg2;
	}

	public void setArg1(K arg1) {
		this.arg1 = arg1;
	}

	public void setArg2(V arg2) {
		this.arg2 = arg2;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		
		if(other instanceof Tuple) {
			if(arg1 == null && ((Tuple<?, ?>) other).getArg1() != null) return false;
			if(arg2 == null && ((Tuple<?, ?>) other).getArg2() != null) return false;
			if(arg1 != null && !arg1.equals(((Tuple<?, ?>) other).getArg1())) return false;
			if(arg2 != null && !arg2.equals(((Tuple<?, ?>) other).getArg2())) return false;
			return true;
		}
		
		if(arg1 != null && arg1.equals(other)) return true;
		if(arg2 != null && arg2.equals(other)) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arg1 == null) ? 0 : arg1.hashCode());
		result = prime * result + ((arg2 == null) ? 0 : arg2.hashCode());
		return result;
	}
}
