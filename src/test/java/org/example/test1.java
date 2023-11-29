package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 {
    @Test
    public void printName() {
        System.out.println("TEst1");
    }
    @Test
    public void printNameFail() {
        Boolean x=false;
        System.out.println("Fail");
        Assert.assertTrue(x);
    }
}
