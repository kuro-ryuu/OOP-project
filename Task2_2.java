import java.util.PriorityQueue;

public class Task2_2 {
    public static void main(String[] args) {
        EventList eventList = new EventList();
        eventList.add(new Event(1, "Customers arrived"));
        eventList.add(new Event(2, "Entered queue"));
        eventList.add(new Event(5, "Dequeued"));
        eventList.add(new Event(6, "Customer being serviced"));
        eventList.add(new Event(10, "Customer served and exited"));

        System.out.println("All events in order:");
        eventList.printOrdered();

        Event next = eventList.pollNext();
        System.out.println("\nRemoved next event to be processed: " + next);

        System.out.println("\nRemaining events in chronological order:");
        eventList.printOrdered();
    }
}

class Event implements Comparable<Event> {
    private final int arrivalTime;
    private final String name;

    public Event(int arrivalTime, String name) {
        this.arrivalTime = arrivalTime;
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Event other) {
        int timeComparison = Integer.compare(this.arrivalTime, other.arrivalTime);
        if (timeComparison != 0) {
            return timeComparison;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Event[time=" + arrivalTime + ", name='" + name + "']";
    }
}

class EventList {
    private final PriorityQueue<Event> eventList = new PriorityQueue<>();

    public void add(Event event) {
        eventList.add(event);
    }

    public Event peekNext() {
        return eventList.peek();
    }

    public Event pollNext() {
        return eventList.poll();
    }

    public boolean isEmpty() {
        return eventList.isEmpty();
    }

    public int size() {
        return eventList.size();
    }

    public void printOrdered() {
        PriorityQueue<Event> copy = new PriorityQueue<>(eventList);
        while (!copy.isEmpty()) {
            System.out.println(copy.poll());
        }
    }
}
