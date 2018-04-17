package cqupt.domain;

import java.util.ArrayList;
import java.util.List;

public class GetMeau {
	public static  List<GoodsMeau>  getMeau(){
		GoodsMeau g1=new GoodsMeau();
		g1.setValue(1);
		g1.setText("西瓜");
		
		GoodsMeau g2=new  GoodsMeau();
		g2.setValue(2);
		g2.setText("iphone");
		
		GoodsMeau g3=new  GoodsMeau();
		g3.setValue(3);
		g3.setText("红牛");
		
		List<GoodsMeau>  list=new ArrayList<GoodsMeau>();
		list.add(g1);
		list.add(g2);
		list.add(g3);
		
		return list;
	}
}
