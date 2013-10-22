package com.uem.app

class Arquivo {
	
	String nomeArquivo = ""
	String nomeOriginal = ""
	String caminhoRelativo = ""
	Integer tamanhoArquivo = 0
	String contentType = ""
	
	static hasMany = [descritores: Descritor]

    static constraints = {
		nomeArquivo blank: true
		nomeOriginal blank: true
		caminhoRelativo blank: true
		tamanhoArquivo blank: true
		contentType blank: true
    }
	
	String toString() {
		"$nomeArquivo : $nomeOriginal : $tamanhoArquivo"	
	};
}
