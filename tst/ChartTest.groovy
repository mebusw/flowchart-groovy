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

    void testParseConnections() {
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
}
