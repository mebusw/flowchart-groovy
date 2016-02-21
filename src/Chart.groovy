import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created by jacky on 15/12/23.
 */
class Chart {
    public static int SYMBOL_START_CX = 80
    public static int SYMBOL_START_CY = 60
    public static int SYMBOL_WIDTH = 150
    public static int SYMBOL_HEIGHT = 100
    public static int LINE_HEIGHT = 100
    public static int LINE_WIDTH = 100

    public Start start = null

    public Map parse(String dsl) {
        def symbols = [:]
        def lines = dsl.trim().split('\n')

        for (line in lines) {
            if (line.contains("=>")) {
                String[] segs = line.split("=>")
                def key = segs[0].trim()

                segs = segs[1].split(":")
                def type = segs[0]

                segs = segs[1].split("[|]")
                def text = segs[0].trim()
                def flowState = segs[1].trim()

                def symbol = generateByType(type, key, text, flowState)
                symbols.put(key, symbol)
                if (type == "start") {
                    this.start = symbol
                    this.start.cx = Chart.SYMBOL_START_CX
                    this.start.cy = Chart.SYMBOL_START_CY
                }

            } else if (line.contains("->")) {
                String[] keys = line.trim().split("->")
                //println("keys=${keys}")
                def Symbol last = null
                def lastPath = "yes"
                def lastDirection = "left"

                for (key in keys) {

                    if (key.contains("(")) {
                        def fields = key.split("\\(|\\)|,")
                        key = fields[0].trim()
                        lastPath = fields[1].trim()
                        if (fields.size() > 2)
                            lastDirection = fields[2]
                    }
                    if (null == last) {
                        /* The first symbol in current line */
                        last = symbols[key]
                        continue
                    }

                    if (last instanceof Condition) {
                        // Default: yes=bottom/left, no=right
                        if (lastPath == "no") {
                            last.no = symbols[key]
                            symbols[key].cx = last.cx + Chart.LINE_WIDTH + Chart.SYMBOL_WIDTH
                            symbols[key].cy = last.cy + Chart.LINE_HEIGHT + Chart.SYMBOL_HEIGHT;
                        } else {
                            last.yes = symbols[key]
                            symbols[key].cx = last.cx
                            symbols[key].cy = last.cy + Chart.LINE_HEIGHT + Chart.SYMBOL_HEIGHT;
                        }
                    } else {
                        last.next = symbols[key]
                        symbols[key].cx = last.cx
                        symbols[key].cy = last.cy + Chart.LINE_HEIGHT + Chart.SYMBOL_HEIGHT;
                    }
                    last = symbols[key]
                }
            }
        }
        return symbols
    }


    public drawToFile() {
        int width = 480, height = 720;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        def curr = this.start
        def graphics = image.getGraphics()

        while (curr) {
            curr.draw(graphics)
            curr = curr.next
        }

        graphics.dispose();
        File f = new File("/Users/jacky/Downloads/flowchart.jpg")
        ImageIO.write(image, "JPEG", f)


    }

    private Symbol generateByType(type, key, text, flowState) {
        Symbol result
        switch (type) {
            case 'start':
                result = new Start(key, text, flowState)
                break
            case 'end':
                result = new End(key, text, flowState)
                break
            case 'operation':
                result = new Operation(key, text, flowState)
                break
            case 'condition':
                result = new Condition(key, text, flowState)
                break
            default:
                throw new Exception(type)
        }
        result
    }


}
