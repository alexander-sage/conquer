package utils;

import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.javatuples.Pair;


/**
 * 
 * @author Alexander
 *
 * @param <T> Type
 * @param <I> Identifier
 */
public abstract class Handler<T,I> {
	
	int size;
	public Handler(int size){
		this.size = size;
	}

	abstract T createElement(I identifier);
	
	LinkedList<Pair<I,T>> contentQueue;
	
	public T getElement(I identifier){
		return getElement(t->true, identifier, (t,i)->t);
	}
	
	public T getElement(Predicate<T> matcher, I identifier){
		return getElement(matcher, identifier, (t,i)->t);
	}
	
	public T getElement(Predicate<T> matcher, I identifier, BiFunction<T,I,T> producer){
		for(Pair<I,T> pair : contentQueue){
			if(pair.getValue0().equals(identifier) || matcher.test(pair.getValue1())){
				return producer.apply(pair.getValue1(), identifier);
			}
		}
		
		T newElement = createElement(identifier);
		
		if(contentQueue.size() > size){
			contentQueue.removeFirst();
		}
		
		contentQueue.add(new Pair<>(identifier, newElement));
		
		return newElement;
	}
	
	
	
	

	
}
