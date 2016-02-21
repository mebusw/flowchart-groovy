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
        g.setColor(Color.BLACK)
        g.drawLine(this.cx, this.cy, this.yes.cx, this.yes.cy)
        g.drawLine(this.cx, this.cy, this.no.cx, this.cy)
        g.drawLine(this.no.cx, this.cy, this.no.cx, this.no.cy)


        g.setColor(Color.GREEN)
        def dx = Chart.SYMBOL_WIDTH / 2 as int
        def dy = Chart.SYMBOL_HEIGHT / 2 as int
        def xs = [cx, cx - dx, cx, cx + dx] as int[]
        def ys = [cy - dy, cy, cy + dy, cy] as int[]
        g.fillPolygon(xs, ys, 4)

        this.yes.draw(g);
        this.no.draw(g);
    }
}