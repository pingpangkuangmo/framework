package com.lg.design.obser.impltest;

import com.lg.design.obser.impl.Observer;

public class BObserver implements Observer<MyObservable> {

	@Override
	public void update(MyObservable t, Object arg) {
		System.out.println("BObserver"+"---->event:"+arg+";data="+t.getData());
	}

}
