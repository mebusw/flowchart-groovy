import java.awt.Color
import java.awt.Graphics

/**
 * Created by jacky on 15/12/23.
 */
class Condition extends Symbol {

    Symbol yes
    Symbol no
    String yesDirection = "left"
    String noDirection = "right"

    Condition(String key, String text, String flowState) {
        super(key, text, flowState)
        yes = no = null
    }

    @Override
    def draw(Graphics g) {
        g.setColor(Color.GREEN)

        def x = this.cx - Chart.SYMBOL_WIDTH / 2
        def y = this.cy - Chart.SYMBOL_HEIGHT / 2
        g.fillRect(x as int, y as int, Chart.SYMBOL_WIDTH, Chart.SYMBOL_HEIGHT)

        //this.next.draw(g);
    }
}