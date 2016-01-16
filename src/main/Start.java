package main;

import jwi.JWIAccessorFacade;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by shahbaaz on 1/16/16.
 */
public class Start {

    public static void main(String args[]) {

        JWIAccessorFacade accessor = new JWIAccessorFacade();
        //accessor.test("Taj Mahal");
        accessor.getClosestWords("Chair",4);
       // System.out.println(StringUtils.getJaroWinklerDistance("chair", "chair"));
    }
}