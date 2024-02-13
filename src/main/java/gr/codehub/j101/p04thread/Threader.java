package gr.codehub.j101.p04thread;

public class Threader extends Thread {

	int no = 0;

	public Threader(int no) {
		this.no = no;
	}

	public void run() {
		int wait = (int)(Math.random() * 1000);
		for (int i = 0; i < 30; i++) {
			System.out.println("Hello from thread " + no + " at " + i);
		}
		System.gc();
		System.out.println("THREAD " + no + " FINISHED ======================================");
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Threader(i).start();
		}
		System.out.println("Finished!");
	}

}
