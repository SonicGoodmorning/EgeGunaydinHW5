public class PrintingTask extends Task {
    private final int totalPages;
    private int pagesPrinted;

    public PrintingTask(String id, int totalPages) {
        super(id);
        if(totalPages <= 0){throw new IllegalArgumentException("Total pages must be positive");}
        this.totalPages = totalPages;
        this.pagesPrinted = 0;
    }

    @Override
    public int execute(int quantum){
        if(quantum <= 0){throw new IllegalArgumentException("Quantum must be positive");}
        if(isComplete()){return 0;}

        int remainingPages = totalPages - pagesPrinted;
        int pagesToPrint = Math.min(quantum, remainingPages); // this was cool i liked learning this
        for(int i = 0; i < pagesToPrint; i++){
            pagesPrinted++;
            System.out.println(getId() + " printed page " + pagesPrinted);
        }
        return pagesToPrint;
    }

    @Override
    public boolean isComplete(){return pagesPrinted >= totalPages;}

    @Override
    public String getDescription(){return getId() + ": " + (totalPages - pagesPrinted) + " pages remaining.";}

}
