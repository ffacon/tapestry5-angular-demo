package dev.openshift.tapestry.angular.data;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BasketDetails {
	
	private HashMap<Long,Long> productList;
	
	private long nbItems;

	public BasketDetails() {
		super();
		// assume a basket for 10 products at start 
		productList = new HashMap<Long,Long>(10);
		// poductId as Long , nb  as long
		nbItems =0;
	}

	public void addToBasket(long ProductId)
	{
		 Long n = productList.get(ProductId);
		  if (n == null) {
			  productList.put(ProductId, 1L);
		  } else {
			  productList.put(ProductId, n + 1);
		  }
		  nbItems++;
	}
	
	public void removeFormBasket(long ProductId)
	{
		 Long n = productList.get(ProductId);
		 if (n == null) {
			  return;
		  } else {
			  if(n==0) return;
			  productList.put(ProductId, n - 1);
		  }
		  nbItems--;
	}
	
	public Long getNb(long ProductId)
	{
		return productList.get(ProductId);		
	}
	
	public Long getNbItems(){
		return  nbItems;
	}
	
	public Set<Long> getProductIDs(){
		return productList.keySet();
	}
	
	
	
}
