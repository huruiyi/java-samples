package com.example.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.service.SomeDataServiceEmptyStub;
import com.example.service.SomeDataServiceOneElementStub;
import com.example.service.SomeDataServiceStub;
import org.junit.jupiter.api.Test;

public class SomeBusinessStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStub());
		int actualResult = business.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = business.calculateSumUsingDataService();//new int[] {}
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceOneElementStub());
		int actualResult = business.calculateSumUsingDataService();//new int[] { 5 }
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}
}
