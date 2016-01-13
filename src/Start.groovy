import java.awt.*

class Start extends Symbol {
    private int width = 20
    private int height = 20
    public final static ARROW_LEN = 40

    Start(String key, String text, String flowState) {
        super(key, text, flowState)
    }

    @Override
    def draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE)

        def int x = width / 2
        def int y = height / 2
        graphics.fillRect(x, y, width, height)
//        graphics.fillRect(0, 0, 40, 40)
        graphics.drawOval(40, 40, 40, 40)

        graphics.setColor(Color.RED)
//        graphics.drawLine(0 + width / 2, height, 0 + width / 2, height + ARROW_LEN);
        graphics.drawLine(2, 30, 2, 50)
    }
}
