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
class ParserTest extends GroovyTestCase {
    void testSymbol() {
        def dsl = "st=>start:beginner  |past"

        def obj = Parser.parse(dsl)

        assertEquals("st", obj.key)
        assertEquals("beginner", obj.text)
        assertEquals("", obj.flowState)
    }

    void testStartSymbol() {
        def symbol = new Start("开始", "st", "abc")
        assertEquals("st", symbol.key)

    }

    void testJSON() {
        def obj = [1, 2, 3, [a: 1, b: 2]]

        assertEquals 1, obj[0]
        assertEquals 2, obj[3]['b']
        assertEquals 1, obj[3]['a']
    }

    void testJSONParsing() {
        def builder = new groovy.json.JsonBuilder()
        def root = builder.people {
            person {
                firstName 'Guillame'
                lastName 'Laforge'
                // Named arguments are valid values for objects too
                address(
                        city: 'Paris',
                        country: 'France',
                        zip: 12345,
                )
                married true
                // a list of values
                conferences 'JavaOne', 'Gr8conf'
            }
        }
        assertTrue root instanceof Map
        assertEquals builder.toString(), '{"people":{"person":{"firstName":"Guillame","lastName":"Laforge","address":{"city":"Paris","country":"France","zip":12345},"married":true,"conferences":["JavaOne","Gr8conf"]}}}'


        def object = new groovy.json.JsonSlurper().parseText '''
            { "simple": 123,
              "fraction": 123.66,
              "exponential": 123e12
            }'''
        assert object instanceof Map
        assert object.simple.class == Integer
        assert object.simple == 123
        assert object.fraction.class == BigDecimal
        assert object.exponential.class == BigDecimal
    }

    void testForLoopOnNull() {
        def l = Arrays.asList(1, 2, 3)
        for (o in l) {
            println(o)
        }

        l = null
        for (o in l) {
            println(o)
        }
    }
}
