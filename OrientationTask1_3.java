import java.util.LinkedList;
import java.util.Scanner;

public class OrientationTask1_3 {
    private static int nextId = 1;
    private int id;
    private long startTime;
    private long endTime;

    public OrientationTask1_3() {
        this.id = nextId++;
        this.startTime = System.currentTimeMillis();
    }

    public OrientationTask1_3(long startTime, long endTime) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    // getters
    public int getId() {
        return id;
    }
    public long getStartTime() {
        return startTime;
    }
    public long getEndTime() {
        return endTime;
    }

    // setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    // Calculate total time spent
    public long getTotalTimeSpent() {
        return endTime - startTime;
    }


    public static void main(String[] args) {

        LinkedList<OrientationTask1_3> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Use:queue, dequeue, exit");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "queue":
                    OrientationTask1_3 OrientationTask1_3 = new OrientationTask1_3();
                    queue.addFirst(OrientationTask1_3);
                    System.out.println("Customer " + OrientationTask1_3.getId() + " queued at " + OrientationTask1_3.getStartTime());
                    break;

                case "dequeue":
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty.");
                        break;
                    }

                    OrientationTask1_3 queuedCustomers = queue.removeLast();
                    queuedCustomers.setEndTime(System.currentTimeMillis());
                    System.out.println("Customer " + queuedCustomers.getId() + " dequeued.");
                    System.out.println("Time spent in queue: " + queuedCustomers.getTotalTimeSpent() + " ms");
                    break;

                case "exit":
                    System.out.println("Program exited.");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}