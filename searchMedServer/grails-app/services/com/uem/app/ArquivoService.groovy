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
}
