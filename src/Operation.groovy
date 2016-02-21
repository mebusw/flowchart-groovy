import java.awt.Color
import java.awt.Graphics

class Operation extends Symbol {

    Operation(String key, String text, String flowState) {
        super(key, text, flowState)
    }

    @Override
    def draw(Graphics g) {
        drawToNexts(g)

        g.setColor(Color.YELLOW)
        def x = this.cx - Chart.SYMBOL_WIDTH / 2
        def y = this.cy - Chart.SYMBOL_HEIGHT / 2
        g.fillRect(x as int, y as int, Chart.SYMBOL_WIDTH, Chart.SYMBOL_HEIGHT)

        askNextToDraw(g)
    }

    private void askNextToDraw(Graphics g) {
        if (this.next)
            this.next.draw(g)
    }

    private void drawToNexts(Graphics g) {
        if (null == this.next)
            return

        g.setColor(Color.BLACK)
        g.drawLine(this.cx, this.cy, this.next.cx, this.next.cy)
    }

}
