/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model;

/**
 * Generic interface for the Observable pattern.
 * 
 * @param <T> Type of observers
 */
public interface Observable<T> {
	/**
	 * Adds an observer.
	 * 
	 * @param o An observer
	 */
	public void addObserver(T o);

	/**
	 * Removes an observer.
	 * 
	 * @param o An observer
	 */
	public void removeObserver(T o);
}