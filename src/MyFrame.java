import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("My Frame");

        //you can set the content pane of the frame
        //to your custom class.

        setContentPane(new DrawPane());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 800);

        setVisible(true);
    }

    //create a component that you can actually draw on.
    class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            //draw on g here e.g.
//            g.fillRect(20, 20, 100, 200);


            String dsl = "st=>start:开始1  |past\n" +
                    "cd1=>condition:分支2  |past\n" +
                    "op1=>operation:操作3  |current\n" +
                    "op2=>operation:操作4  |current\n" +
                    "e=>end:结束5  |future\n" +
                    "st->cd1\n" +
                    "cd1(no, left)->op2\n" +
                    "cd1(yes, right)->op1->e\n";


            Chart chart = new Chart();
            Map<String, Symbol> symbols = chart.parse(dsl);
            //symbols.get("st").draw(g);
            chart.start.draw(g);



        }
    }

    public static void main(String args[]) {
        new MyFrame();
    }

}
