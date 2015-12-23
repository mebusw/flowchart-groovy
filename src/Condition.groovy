/**
 * Created by jacky on 15/12/23.
 */
class Condition extends Symbol {

    Symbol left
    Symbol right

    Condition(String text, String key, String flowState) {
        super(text, key, flowState)
    }
}
