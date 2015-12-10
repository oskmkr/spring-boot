package com.oskm.util;
/*
import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import org.apache.log4j.Logger;

public class Java7NewFeatures {

	private static final Logger LOG = Logger.getLogger(Java7NewFeatures.class);

	public Map<String, Object> diamondDrectives() {

		Map<String, Object> map = new HashMap<>();

		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");

		return map;

	}

	public String stringSwitch(String label) {

		switch (label) {
		case "oskmkr":
			return "oskmkr";
		case "oskm2":
			return "oskm2";
		case "oskm3":
			return "oskm3";
		default:
			return "default";
		}
	}

	public int numberUnderscore() {

		int n = 1_0_0_0;

		return n / 10;

	}

	public void multiCatch(boolean flag) throws CompositeException {
		try {
			throwExceptionMethod(flag);
		} catch (Exception1 | Exception2 e) {
			LOG.debug(e, e);
			throw new CompositeException();
		}
	}

	public void fileChangeNotification() {

		Path path = Paths.get("d:\\");

		WatchService watchService = null;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
		} catch (IOException e) {
			LOG.error(e, e);
		}

		while (true) {
			WatchKey key = null;
			try {
				key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Kind<?> kind = event.kind();
					LOG.debug("Event on " + event.context().toString() + " is " + kind);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean reset = key.reset();

			if (!reset) {
				break;
			}

		}

	}

	
	public void forkJoin() {
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();

		LOG.debug("number of processors : " + numberOfProcessors);

		ForkJoinPool forkJoinPool = new ForkJoinPool(numberOfProcessors);

		

		forkJoinPool.invoke(new MyBigProblemTask());

		

		
		
		this.bigProblem();
		
		
	}

	private void throwExceptionMethod(boolean flag) throws Exception1, Exception2 {

		if (flag) {
			throw new Exception1();
		}

		throw new Exception2();

	}
	
	private void bigProblem() {
		int j = 0;
		long startTime = System.currentTimeMillis();
		
		for (int i = 1; i < 1000000000; i++) {
			j += i;
		}
		
		LOG.debug("result : " + j);
		LOG.debug("ellapsed time : " + (System.currentTimeMillis() - startTime));
		
	}
}

class Exception1 extends Exception {

}

class Exception2 extends Exception {

}

class CompositeException extends Exception {

}

class MyBigProblemTask extends RecursiveAction {

	private static final Logger LOG = Logger.getLogger(MyBigProblemTask.class);

	@Override
	protected void compute() {
		int j = 0;
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 1000000000; i++) {
			j += i;
		}
		LOG.debug("ellapsed time : " + (System.currentTimeMillis() - startTime));
		LOG.debug("result : " + j);
	}

}
*/