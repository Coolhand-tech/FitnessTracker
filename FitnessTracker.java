import java.time.LocalDateTime;
import java.util.*;

class Exercise {
    private String name;
    private int reps, caloriesPerRep;
    public Exercise(String name, int reps, int caloriesPerRep) {
        this.name = name;
        this.reps = reps;
        this.caloriesPerRep = caloriesPerRep;
    }
    public int getCalories() { return reps * caloriesPerRep; }
    public String toString() { return name + " (" + reps + " reps)"; }
}

class Workout {
    private List<Exercise> exercises = new ArrayList<>();
    private LocalDateTime date;
    public Workout() { this.date = LocalDateTime.now(); }
    public void addExercise(String name, int reps, int calPerRep) {
        exercises.add(new Exercise(name, reps, calPerRep));
    }
    public int totalCalories() { return exercises.stream().mapToInt(Exercise::getCalories).sum(); }
    public String toString() { return date + ": " + exercises + " (" + totalCalories() + " cal)"; }
}

class FitnessTracker {
    private List<Workout> workouts = new ArrayList<>();

    public void addWorkout(Workout w) {
        workouts.add(w);
        System.out.println("Workout added!");
    }

    public void stats() {
        if (workouts.isEmpty()) {
            System.out.println("No workouts yet.");
            return;
        }
        int totalCal = workouts.stream().mapToInt(Workout::totalCalories).sum();
        System.out.println("Total Workouts: " + workouts.size());
        System.out.println("Total Calories Burned: " + totalCal);
        workouts.forEach(System.out::println);
    }

    public static void main(String[] args) {
        FitnessTracker ft = new FitnessTracker();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Workout\n2. View Stats\n3. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();
            try {
                if (choice.equals("1")) {
                    Workout w = new Workout();
                    while (true) {
                        System.out.print("Exercise Name (or 'done'): ");
                        String name = sc.nextLine();
                        if (name.equals("done")) break;
                        System.out.print("Reps: ");
                        int reps = Integer.parseInt(sc.nextLine());
                        System.out.print("Calories per Rep: ");
                        int cal = Integer.parseInt(sc.nextLine());
                        w.addExercise(name, reps, cal);
                    }
                    ft.addWorkout(w);
                } else if (choice.equals("2")) {
                    ft.stats();
                } else if (choice.equals("3")) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}