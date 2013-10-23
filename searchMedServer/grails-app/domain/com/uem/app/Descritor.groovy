package com.uem.app

import sun.org.mozilla.javascript.internal.IdScriptableObject;

class Descritor {
	
	static mapping = {
	}
	
	String idDecs
	String nome
	
    static constraints = {
		idDecs blank: false, unique: true
		nome blank: false
    }
	
	@Override
	public String toString() {
		"$nome"
	}
}
