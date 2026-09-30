public class ComputationTask extends Task {
    private int remainingSteps;

    public ComputationTask(String id, int totalSteps) {
        super(id);
        if(totalSteps <= 0){throw new IllegalArgumentException("Total steps must be positive");}
        this.remainingSteps = totalSteps;
    }

    @Override
    public int execute(int quantum){
        if(quantum <= 0){throw new IllegalArgumentException("Quantum must be positive");}
        if(isComplete()){return 0;}

        int stepsToExecute = Math.min(quantum, remainingSteps);
        remainingSteps -= stepsToExecute;
        return stepsToExecute;
    }

    public boolean isComplete(){return remainingSteps == 0;}

    public String getDescription(){return getId() + ": " + remainingSteps + " computation steps remaining.";}
    // Implement the three abstract methods.
}
