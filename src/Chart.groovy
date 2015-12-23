/**
 * Created by jacky on 15/12/23.
 */
class Chart {
    Symbol head = null
    Symbol curr = null
    List symbols

    public List parse(String dsl) {
        symbols = []
        def lines = dsl.trim().split('\n')

        for (line in lines) {
            String[] segs = line.split("=>")
//            println(segs)
            def key = segs[0].trim()

            segs = segs[1].split(":")
//            println(segs)
            def type = segs[0]

            segs = segs[1].split("[|]")
//            println(segs)
            def text = segs[0].trim()
            def flowState = segs[1].trim()

            symbols << generateByType(type, key, text, flowState)
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
            default:
                throw new Exception(type)
        }
        result
    }
}
