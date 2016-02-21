import java.awt.Color
import java.awt.Graphics

/**
 * Created by jacky on 15/12/23.
 */
class End extends Symbol {

    End(String key, String text, String flowState) {
        super(key, text, flowState)
    }

    @Override
    def draw(Graphics g) {
        g.setColor(Color.BLUE)
        def diameter = Chart.SYMBOL_WIDTH * 0.3 as int
        def radius = diameter / 2 as int
        g.fillOval(cx - radius, cy - radius, diameter, diameter)

    }
}
