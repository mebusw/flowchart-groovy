/**
 * Created by jacky on 15/12/23.
 */
class Symbol {
    Symbol next
    String text
    String key
    String flowState

    Symbol(String text, String key, String flowState = "") {
        this.text = text
        this.key = key
        this.flowState = flowState
        this.next = null
    }

}
