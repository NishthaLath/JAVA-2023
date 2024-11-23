//2022427833 니스타

package callbackInterface;

class WorkerThread extends Thread {
	String name;
	SimpleInfCallback.Callback callback;
	
	public WorkerThread(String name, 
						SimpleInfCallback.Callback callback) {
		this.name = name;
		this.callback = callback;
	}
	
	@Override 
	public void run() {
		for(int i=0; i<10; i++) {
			this.callback.printResult(name, i);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
	}
}

public class SimpleInfCallback {
	// 중첩 인터페이스 선언 
	interface Callback {
		void printResult(String name, int n);
	}
	
	public static void main(String[] args) {
		// 중첩 인터페이스 구현 
		Callback callback = new Callback() {
			@Override
			public void printResult(String name, int n) {				
				System.out.printf("%s thread (n=%d)\n", name, n);				
			}			
		};
		
		WorkerThread t1 = new WorkerThread("First", callback);
		WorkerThread t2 = new WorkerThread("Second", callback);
		
		t1.start();
		t2.start();		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Threads terminated.");
	}
}
