package com.uem.app

import grails.transaction.Transactional

@Transactional
class ArquivoService {

    def setArquivoSalvaFile(Arquivo arquivoInstance, uploadedFile, webRootDir) {
		if(!uploadedFile.empty){
			arquivoInstance.nomeArquivo = uploadedFile.name
			arquivoInstance.nomeOriginal = uploadedFile.originalFilename
			arquivoInstance.tamanhoArquivo = uploadedFile.size
			arquivoInstance.contentType = uploadedFile.contentType
		}

		File userDir = new File(webRootDir, "/files")
		userDir.mkdirs()
		File destino = new File(userDir, uploadedFile.originalFilename)

		try {
			uploadedFile.transferTo(destino)
			arquivoInstance.caminhoRelativo = "${userDir.path}/${uploadedFile.originalFilename}"
			println arquivoInstance.caminhoRelativo
		} catch (Exception e) {
			e.printStackTrace()
		}
    }
	
	def getDescritoresFree(Arquivo arquivo) {
		def query = "select distinct d from Descritor as d where d not in (select dca from Arquivo as a join a.descritores as dca)"
		List descritores = Descritor.executeQuery(query)
		if (arquivo) 
			arquivo.descritores.each{ descritores.add(it) }
		descritores  
	}
	
	def getDescritoresFree() {
		getDescritoresFree(null)
	}
}
