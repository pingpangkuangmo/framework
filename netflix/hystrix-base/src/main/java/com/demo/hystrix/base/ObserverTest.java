package com.demo.hystrix.base;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class ObserverTest {

	@Test
	public void test1(){
		Observable<String> myObservable = Observable.create(  
		    new Observable.OnSubscribe<String>() {  

				@Override
				public void call(Subscriber<? super String> t) {
					t.onNext("hello rx");
					t.onCompleted();
				}  
		    }  
		);
		
		Subscriber<String> mySubscriber = new Subscriber<String>() {  
		    @Override  
		    public void onNext(String s) { 
		    	System.out.println(s); 
		    }  
		  
		    @Override  
		    public void onCompleted() { }  
		  
		    @Override  
		    public void onError(Throwable e) { }  
		};
		
		myObservable.subscribe(mySubscriber);
		System.out.println("xxxxxx");
		myObservable.subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("subscribe2 " + t);
			}
			
		});
	}
	
	@Test
	public void test2(){
		Observable<String> myObservable = Observable.just("hello rx");
		myObservable.map(new Func1<String, Integer>(){

			@Override
			public Integer call(String t) {
				return t.hashCode();
			}
				
		});
		myObservable.subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("subscribe2 " + t);
			}
			
		});
	}
}
