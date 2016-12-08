package com.demo.openhft;

import net.openhft.chronicle.hash.impl.VanillaChronicleHash;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import net.openhft.chronicle.map.VanillaChronicleMap;

public class Main {
	
	VanillaChronicleHash vanillaChronicleHash;
	VanillaChronicleMap vanillaChronicleMap;

	public static void main(String[] args){
		ChronicleMap<String, Object> cityPostalCodes = ChronicleMapBuilder
			    .of(String.class, Object.class)
			    .name("city-postal-codes-map")
			    .averageKey("Amsterdam")
			    .entries(50000)
			    .create();
		
		cityPostalCodes.put("nanyang", new Object());
	}
}
