package fr.uge.jee.printers;

public class FrenchMessagePrinter implements MessagePrinter{
    @Override
    public void printMessage() {
        System.out.println("Bonjour Monde!");
    }
}
