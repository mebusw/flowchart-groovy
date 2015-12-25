/**
 * Created by jacky on 15/12/23.
 */
class Chart {
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

                symbols.put(key, generateByType(type, key, text, flowState))
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
                        last = symbols[key]
                        continue
                    }

                    if (last instanceof Condition) {
                        if (lastPath == "no") {
                            last.no = symbols[key]
                            last.yesDirection = lastDirection
                        } else {
                            last.yes = symbols[key]
                            last.noDirection = lastDirection
                        }
                    } else {
                        last.next = symbols[key]
                    }
                    last = symbols[key]
                }
            }
        }
        symbols
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
