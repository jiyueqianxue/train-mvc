package io.mine.ft.train.service.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class ShopExecutor implements Executor {
	
	
	private Executor executor = Executors.newSingleThreadExecutor();

	@Override
	public void execute(Runnable command) {
		
		executor.execute(command);
	}
	
}
