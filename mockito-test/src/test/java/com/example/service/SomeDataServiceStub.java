package com.example.service;

import com.example.data.SomeDataService;

public class SomeDataServiceStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 1, 2, 3 };
	}
}
