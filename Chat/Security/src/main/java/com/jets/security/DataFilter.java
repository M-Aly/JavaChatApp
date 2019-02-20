package com.jets.security;

public interface DataFilter {
	byte filter(byte dataIn);
	default byte[] filter(byte[] dataIn) {
		byte[] dataOut=new byte[dataIn.length];
		for(int i=0;i<dataIn.length;i++) {
			dataOut[i]=filter(dataIn[i]);
		}
		return dataOut;
	}
}
