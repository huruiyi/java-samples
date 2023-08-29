package com.example.service;

import com.example.data.SomeDataService;

public class SomeDataServiceEmptyStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { };
	}
}
