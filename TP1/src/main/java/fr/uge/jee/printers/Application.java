package fr.uge.jee.printers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
//        var printer = new SimpleMessagePrinter();
//        printer.printMessage();
        ApplicationContext context = new ClassPathXmlApplicationContext("config-printers.xml");
        MessagePrinter printer = context.getBean("MessagePrinter",MessagePrinter.class);
        printer.printMessage();
        MessagePrinter frenchPrinter = context.getBean("FrenchMessagePrinter",MessagePrinter.class);
        frenchPrinter.printMessage();
        MessagePrinter customizablePrinter = context.getBean("CustomizableMessagePrinter",MessagePrinter.class);
        customizablePrinter.printMessage();
        MessagePrinter printer1 = context.getBean("CountMessagePrinter",MessagePrinter.class);
        printer1.printMessage();
        printer1.printMessage();
        printer1.printMessage();
        MessagePrinter printer2 =  context.getBean("CountMessagePrinter",MessagePrinter.class);
        printer2.printMessage();
        MessagePrinter slowprinter = context.getBean("SlowConstructionMessagePrinter",MessagePrinter.class);
        slowprinter.printMessage();
    }
}

/**
 * Expliquez l'affichage ci-dessous ? Quel design pattern est mis en oeuvre par le conteneur IoC ?
 *
 * on a fait 2 appels a getBean differents et donc on devrais avoir 0 1 2 0 mais on a 0 1 2 3 ce qui signifie que les deux getBean partagent la même instance
 * Le design pattern mis en place par le conteneur IoC est un singleton
 *
 *
 *
 * A quel moment sont instantiés les beans ?
 *
 * Les beans sont instantiés au moment de la création de l'application
 *
 *
 *
 * Quel est l'inconvéniant majeur de déférer l'instantition des beans ?
 */