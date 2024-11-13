package fr.uge.jee.printers;

public class SlowConstructionMessagePrinter implements MessagePrinter{

    private String message;
    SlowConstructionMessagePrinter (String message) throws InterruptedException {
        Thread.sleep(5000);
        this.message=message;
    }
    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
