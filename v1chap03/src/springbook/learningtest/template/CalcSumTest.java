package springbook.learningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
public class CalcSumTest {
	Calculator calculator;
	String numFilepath;
	@Before public void setUp(){
		this.calculator = new Calculator();
		this.numFilepath = "numbers.txt";
	}
	
	@Test
	public void sumOfNumbers() throws IOException{
		int sum = this.calculator.calcSum(getClass().getResource(this.numFilepath).getPath());
		assertThat(sum,is(10));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException{
		int multiply = this.calculator.calcMultiply(getClass().getResource(this.numFilepath).getPath());
		assertThat(multiply,is(24));
	}
	
	@Test
	public void concatenateStrings() throws IOException{
		assertThat(this.calculator.concatenate(getClass().getResource(this.numFilepath).getPath()), is("1234"));
	}
}
