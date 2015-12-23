/**
 * Created by jacky on 15/12/23.
 */
class Condition extends Symbol {

    Symbol left
    Symbol right

    Condition(String key, String text, String flowState) {
        super(key, text, flowState)
    }
}
