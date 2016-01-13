import java.awt.*

class Symbol {
    Symbol next
    String text
    String key
    String flowState
    boolean isConnected


    Symbol(String key, String text, String flowState = "") {
        this.key = key
        this.text = text
        this.flowState = flowState
        this.next = null
        isConnected = false
    }

    def draw(Graphics graphics) {
    }
}
