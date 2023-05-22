package com.bookStore.global;

import java.util.ArrayList;
import java.util.List;

import com.bookStore.model.Product;

public class Global {
	
	public static List<Product> cart;
	static {
		
		cart = new ArrayList<Product>();
	}

}
