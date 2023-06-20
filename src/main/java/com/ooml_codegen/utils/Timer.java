package com.ooml_codegen.utils;

public class Timer {

	private long startTime, endTime;

	public void start() { this.startTime = System.currentTimeMillis(); }

	public void stop() { this.endTime = System.currentTimeMillis(); }

	public long getTime() {  return this.endTime - this.startTime; }

}
