import core.basesyntax.model.Fruit;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationValidator;
import core.basesyntax.service.TransactionParser;
import core.basesyntax.service.impl.OperationValidatorImpl;
import core.basesyntax.service.impl.TransactionParserImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TransactionParserImplTest {
    private static List<String> testData;
    private static TransactionParser testParser;

    @BeforeClass
    public static void setUp() {
        OperationValidator validator = new OperationValidatorImpl();
        testParser = new TransactionParserImpl(validator);
        testData = new ArrayList<>();
        testData.add("type,fruit,quantity");
        testData.add("b,apple,100");
    }

    @Test
    public void parsTransaction_ok() {
        List<FruitTransaction> expected = new ArrayList<>();
        expected.add(new FruitTransaction("b", new Fruit("apple"), 100));
        List<FruitTransaction> actual = testParser.parseTransactions(testData);
        Assert.assertEquals(expected.get(0).getFruit(), actual.get(0).getFruit());
        Assert.assertEquals(expected.get(0).getCount(), actual.get(0).getCount());
        Assert.assertEquals(expected.get(0).getOperation(), actual.get(0).getOperation());
    }

}
