package com.openeyes.notifications.service;

public class Crawler {
	private final int NUM_THREADS;
	
	public Crawler(){
		//initialising number of threads
		NUM_THREADS = 2;
	}

	public Crawler checking(){
		return this;
	}
}
