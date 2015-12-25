/**
 * Created by jacky on 15/12/23.
 */
class Condition extends Symbol {

    Symbol yes
    Symbol no
    String yesDirection = "left"
    String noDirection = "right"

    Condition(String key, String text, String flowState) {
        super(key, text, flowState)
        yes = no = null
    }
}
