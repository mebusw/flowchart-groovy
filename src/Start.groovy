import java.awt.*

class Start extends Symbol {
    Start(String key, String text, String flowState) {
        super(key, text, flowState)
    }

    @Override
    def draw(Graphics g) {
        g.setColor(Color.BLACK)
        g.drawLine(this.cx, this.cy, this.next.cx, this.next.cy)

        g.setColor(Color.ORANGE)
        def diameter = Chart.SYMBOL_WIDTH * 0.3 as int
        def radius = diameter / 2 as int
        g.fillOval(cx - radius, cy - radius, diameter, diameter)

        this.next.draw(g)
    }
}
