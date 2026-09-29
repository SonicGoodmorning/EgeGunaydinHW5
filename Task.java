abstract public class Task{
    private final String id;

    public Task(String id){this.id = id;}

    public String getId(){return this.id;}
    public abstract int execute(int quantum);
    public abstract boolean isComplete();
    public abstract String getDescription();

}
