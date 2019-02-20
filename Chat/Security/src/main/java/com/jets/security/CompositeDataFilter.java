package com.jets.security;

import java.util.ArrayList;
import java.util.List;

public class CompositeDataFilter implements DataFilter {
	private List<DataFilter> filterList=new ArrayList<>();
	@Override
	public byte filter(byte dataIn) {
		byte dataOut=dataIn;
		for(DataFilter filter:filterList) {
			dataOut=filter.filter(dataOut);
		}
		return dataOut;
	}
	public void addFilter(DataFilter filter) {
		filterList.add(filter);
	}
	public void removeFilter(DataFilter filter) {
		filterList.remove(filter);
	}
}
