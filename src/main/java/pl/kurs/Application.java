package pl.kurs;

import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;
import pl.kurs.services.IEquationSolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        IEquationSolver equationSolver = ctx.getBean(IEquationSolver.class);

        String inputEquation = null;
        if (args.length > 0) {
            inputEquation = args[0];
        }
        try {
            System.out.println(equationSolver.solve(inputEquation));
        } catch (InvalidEquationFormatException | UnknownOperatorException e) {
            e.printStackTrace();
        }

    }
}
