import java.awt.*

class Start extends Symbol {
    Start(String key, String text, String flowState) {
        super(key, text, flowState)
        this.cx = 50
        this.cy = 50
    }

    @Override
    def draw(Graphics g) {
        g.setColor(Color.ORANGE)

        g.fillOval(cx, cy, (int)(Chart.SYMBOL_HEIGHT * 0.5), (int)(Chart.SYMBOL_HEIGHT * 0.5))

//        g.fillRect(0, 0, 40, 40)

//        g.setColor(Color.RED)
//        g.drawLine(0 + width / 2, height, 0 + width / 2, height + ARROW_LEN);
//        g.drawLine(2, 30, 2, 50)
    }
}
