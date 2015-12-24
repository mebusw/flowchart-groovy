/**
 st=>start:  |past
 e=>end:  | future
 op1=>operation: 初始化理算数据|past
 op2=>operation: 自动理算规则|past
 op3=>operation: 自动理算|future
 op4=>operation: 人工支付规则|future
 op5=>operation: 人工支付|future
 op6=>operation: 自动分配金额|future
 op7=>operation: 人工理算|current

 c1=>condition: 人工还是自动？|approved
 c2=>condition: 人工还是自动？|future


 st->op1->op2->c1
 c1(no)->op3->op4->c2
 c1(yes, right)->op7->e
 c2(yes, right)->op5->e
 c2(no, left)->op6->e(right)
 */

/**
 * key=>symbolType: text|flowState:>link[target]
 * symbol(next, direction)->symbol(...
 *
 * Chart-startSymbol-nextPath
 */
class ChartTest extends GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void testParseBasicSymbols() {
        def dsl = """
            st=>start:开始  |past
            op1=>operation:操作1  |current
            e=>end:结束  |future
            st->op1->e
            """

        def symbols = new Chart().parse(dsl) as Map<Symbol>
        def symbol1 = symbols['st']
        assertEquals("st", symbol1.key)
        assertEquals("开始", symbol1.text)
        assertEquals("past", symbol1.flowState)
        assertTrue symbol1 instanceof Start

        def symbol2 = symbols['op1']
        assertEquals("op1", symbol2.key)
        assertEquals("操作1", symbol2.text)
        assertEquals("current", symbol2.flowState)
        assertTrue symbol2 instanceof Operation

        def symbol3 = symbols['e']
        assertEquals("e", symbol3.key)
        assertEquals("结束", symbol3.text)
        assertEquals("future", symbol3.flowState)
        assertTrue symbol3 instanceof End

        assertEquals(symbol2, symbol1.next)
        assertEquals(symbol3, symbol2.next)
        assertEquals(null, symbol3.next)

    }

    void testParseConditionSymbols() {
        def dsl = """
            st=>start:开始1  |past
            cd1=>condition:分支2  |past
            op1=>operation:操作3  |current
            op2=>operation:操作4  |current
            e=>end:结束5  |future
            st->cd1
            cd1(no)->op2
            cd1->op1->e
            """

        def symbols = new Chart().parse(dsl) as Map<Symbol>
        def st = symbols['st']
        assertEquals("st", st.key)
        assertEquals("开始1", st.text)
        assertEquals("past", st.flowState)
        assertTrue st instanceof Start

        def cd1 = symbols['cd1']
        assertEquals("cd1", cd1.key)
        assertEquals("分支2", cd1.text)
        assertEquals("past", cd1.flowState)
        assertTrue cd1 instanceof Condition

        def op1 = symbols['op1']
        assertEquals("op1", op1.key)
        assertEquals("操作3", op1.text)
        assertEquals("current", op1.flowState)
        assertTrue op1 instanceof Operation

        def op2 = symbols['op2']
        assertEquals("op2", op2.key)
        assertEquals("操作4", op2.text)
        assertEquals("current", op2.flowState)
        assertTrue op2 instanceof Operation

        def e = symbols['e']
        assertEquals("e", e.key)
        assertEquals("结束5", e.text)
        assertEquals("future", e.flowState)
        assertTrue e instanceof End

        assertEquals(cd1, st.next)
        assertEquals(op1, cd1.yes)
        assertEquals(e, op1.next)
        assertEquals(null, e.next)
        assertEquals(op2, cd1.no)

    }
}
