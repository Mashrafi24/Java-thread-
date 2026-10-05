class CookingTask {

    int count = 0;

    CookingTask() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) {
        CookingTask t1 = new CookingTask();
        CookingTask t2 = new CookingTask();
        CookingTask t3 = new CookingTask();

        System.out.println(t1.count);
        System.out.println(t2.count);
        System.out.println(t3.count);
    }
}