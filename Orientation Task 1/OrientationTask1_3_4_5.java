import java.util.LinkedList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.ZoneId;


// taks 4
public class OrientationTask1_3_4_5 {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static String formatTime(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).format(FORMATTER);
    }

    public static void main(String[] args) {
        ServicePoint servicePoint = new ServicePoint();
        Scanner scanner = new Scanner(System.in);
        System.out.println("> (q)ueue, (d)equeue, (s)erve, (e)xit");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "q":
                    Customer newCustomer = new Customer();
                    servicePoint.addToQueue(newCustomer);
                    System.out.println("Customer " + newCustomer.getId() + " queued at " + formatTime(newCustomer.getStartTime()));
                    break;

                case "d":
                    Customer customer = servicePoint.removeFromQueue();
                    if (customer != null) {
                        customer.setEndTime(System.currentTimeMillis());
                        System.out.println("Customer " + customer.getId() + " dequeued at " + formatTime(customer.getEndTime()));
                        System.out.println("Total time spent: " + customer.getTotalTimeSpent()/1000 + "s");
                    }
                    break;

                case "s":
                    if (servicePoint.isEmpty()) {
                        System.out.println("Service queue is empty.");
                        break;
                    }

                    else {
                        System.out.println("Servving customer(s)....");
                    }
                    servicePoint.serve();
                    break;

                case "e":
                    System.out.println("Program exited.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}


// task 3
class Customer {
    private static int nextId = 1;
    private int id;
    private long startTime;
    private long endTime;

    public Customer() {
        this.id = nextId++;
        this.startTime = System.currentTimeMillis();
    }

    public Customer(long startTime, long endTime) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    // getters p
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

    public long getTotalTimeSpent() {
        return endTime - startTime;
    }
}


// task 5
class ServicePoint {
    private LinkedList<Customer> servingQueue;
    public ServicePoint() {
        this.servingQueue = new LinkedList<>();
    }

    public void addToQueue(Customer a) {
        servingQueue.addLast(a);
    }

    public boolean isEmpty() {
        return servingQueue.isEmpty();
    }

    public Customer removeFromQueue() {
        if (servingQueue.isEmpty()) {
            System.out.println("Service queue is empty.");
            return null;
        }
        Customer customer = servingQueue.removeFirst();
        return customer;
    }

    public void serve() {
        while (!servingQueue.isEmpty()) {
            //sleep time (simulataed wait time/serve time)
            long sleeptime = (long) (Math.random() * 5000);

            try {
                //delay
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Service interrupted.");
                return;
            }
            
            //remove and serve
            Customer customer = removeFromQueue();
            if (customer != null) {
                customer.setEndTime(System.currentTimeMillis());
                System.out.println("Customer " + customer.getId() + " service completed. Service time: " + sleeptime/1000 + "s");
            }
        }
    }
}