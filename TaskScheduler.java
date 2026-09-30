import java.util.*;

public class TaskScheduler{
    private CircularLinkedList<Task> tasks = new CircularLinkedList<>();

    public void addTask(Task task){
        if(task == null){throw new IllegalArgumentException("Task cannot be null");}

        boolean dupe = false;
        int s = tasks.size();

        for(int i = 0; i < s; i++){
            Task current = tasks.getFirst();
            if(Objects.equals(current.getId(), task.getId())){dupe = true;}
            tasks.rotate();
        }
        if(dupe){throw new IllegalArgumentException("Duplicate Task");}

        tasks.addLast(task);
    }

    public boolean cancelTask(String id){
        Task taskToRemove = null;
        int c = tasks.size();

        for(int i = 0; i < c; i++){
            Task current = tasks.getFirst();
            if(taskToRemove == null && Objects.equals(current.getId(), id)){
                taskToRemove = current;
            }
            tasks.rotate();
        }
        if(taskToRemove == null){return false;}

        return tasks.remove(taskToRemove);
    }

    public void runOneTurn(int quantum){
        if(quantum <= 0){throw new IllegalArgumentException("Quantum must be positive");}
        if(tasks.size() == 0){return;}

        if(tasks.isEmpty()){return;}

        Task current = tasks.getFirst();
        current.execute(quantum);

        if(current.isComplete()){tasks.removeFirst();}
        else{tasks.rotate();}
    }

    public void runUntilEmpty(int quantum){
        if(quantum <= 0){throw new IllegalArgumentException("Quantum must be positive");}

        while(!tasks.isEmpty()){
            runOneTurn(quantum);
        }
    }

    public String getStatus(){
        if(tasks.isEmpty()){return "[]";}

        StringBuilder sb = new StringBuilder("["); // i didnt know i could instantly declare this is cool
        int c = tasks.size();

        for(int i = 0; i < c; i++){
            sb.append(tasks.getFirst().getDescription());

            if(i < c - 1){sb.append(", ");}

            tasks.rotate();
        }

        sb.append("]");
        return sb.toString();
    }
}
