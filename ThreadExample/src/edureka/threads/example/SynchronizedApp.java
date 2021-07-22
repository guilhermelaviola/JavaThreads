package edureka.threads.example;

class Printer{
	// synchronized void printDocuments(int numOfCopies, String docName)
	void printDocuments(int numOfCopies, String docName) {
		for(int i = 0; i <= numOfCopies; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Document # " + docName + " # " + i);
		}
	}
}

class ThreadOne extends Thread {

	Printer pRef;

	ThreadOne(Printer p){
		pRef = p;
	}

	@Override
	public void run() {
		synchronized (pRef) {
			pRef.printDocuments(9, "Resume_Example1.pdf");
		}
	}
}

class ThreadTwo extends Thread {

	Printer pRef;

	ThreadTwo(Printer p){
		pRef = p;
	}

	@Override
	public void run() {
		synchronized (pRef) {
			pRef.printDocuments(7, "Resume_Example2.pdf");
		}
	}
}

public class SynchronizedApp {

	public static void main(String[] args) {
		System.out.println("---THE APPLICATION HAS STARTED---");

		// We have only one single object of Printer
		Printer printer = new Printer();
		//printer.printDocuments(8, "WageBill_June.pdf");

		// Case scenario: we're having multiple Threads working on the same Object
		// If multiple Threads are going to work on the same Object, then we must synchronize them
		ThreadOne refOne = new ThreadOne(printer); // ThreadOne is having reference from the Printer Object
		ThreadTwo refTwo = new ThreadTwo(printer); // ThreadTwo is having reference from the Printer Object

		refOne.start();

		// The Synchronization comes to the Action below:
		//		try {
		//			refOne.join();
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}

		refTwo.start();

		System.out.println("---THE APPLICATION HAS FINISHED---");
	}

}
