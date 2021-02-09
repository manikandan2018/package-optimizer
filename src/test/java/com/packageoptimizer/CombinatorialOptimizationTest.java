package com.packageoptimizer;
 
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class CombinatorialOptimizationTest {

	  @Test
	   public void testFindMax(){
	       Product[] products = {
	           new Product(1, 10.0, 20.0),
	           new Product(2, 15.0, 22.0),
	           new Product(3, 8.0, 25.0)
	       };
	       BitSet max = new CombinatorialOptimization(products).findMax(32);
	       String result = max.stream().map(index -> products[index].getNumber()).sorted().mapToObj(Objects::toString).collect(Collectors.joining(","));
	       Assertions.assertEquals("2,3", result);
	   }

	   @SuppressWarnings("ConstantConditions")
	   @Test
	   public void testFindMaxEmptyProducts(){
	       Product[] products = {};
	       BitSet max = new CombinatorialOptimization(products).findMax(32);
	       String result = max.stream().map(index -> products[index].getNumber()).sorted().mapToObj(Objects::toString).collect(Collectors.joining(","));
	       Assertions.assertEquals("", result);
	   }
}