package edureka.threads.example;

//class TaskOne {
//	void executeTask() {
//		for (int doc = 0; doc < 10; doc++) {
//			System.out.println("Document # " + doc + "from printer B.");
//		}
//	}
//}

class CA {

}

// class TaskOne extends CA, Thread { // Multiple Inheritance isn's supported in Java
class TaskOne extends CA implements Runnable {
	@Override
	public void run() {
		for (int doc = 0; doc <= 10; doc++) {
			System.out.println("Document # " + doc + " from printer B.");
		}
	}
}

class TaskTwo extends CA implements Runnable {
	@Override
	public void run() {
		for (int doc = 0; doc <= 10; doc++) {
			System.out.println("Document # " + doc + " from printer C.");
		}
	}
}

public class App {

	// The main method represents the main thread
	public static void main(String[] args) {
		// Whatever we write in here will be executed by then main thread
		// Threads always execute the jobs in a sequence
		// Execution context

		// Job 1
		System.out.println("---THE APPLICATION HAS STARTED---");

		// Job 2
		//		TaskOne task = new TaskOne(); // Child Thread / Worker Thread
		//		// task.executeTask();
		//		task.start(); // A start shall internally execute Run();

		Runnable r = new TaskOne();
		Thread task = new Thread();
		task.setDaemon(true);
		task.start();

		//		Thread taskTwo = new Thread(new TaskTwo());
		//		taskTwo.start();

		new Thread(new TaskTwo()).start();

		// Until Job 2 is not finished, below written jobs are not executed
		// In case Job 2 is a long operation (like, for example, printing thousands of documents), the OS or JVM shall give a message that the application is not responding 
		// Some sluggish behavior in the app can be noticed: App seems to hang 

		// Now Main and TaskOne are being executed parallelly or concurrently.

		// Job 3
		for (int doc = 0; doc <= 10; doc++) {
			System.out.println("Document # " + doc + " from printer A.");
		}

		// Job 4
		System.out.println("---THE APPLICATION HAS FINISHED---");
	}
}