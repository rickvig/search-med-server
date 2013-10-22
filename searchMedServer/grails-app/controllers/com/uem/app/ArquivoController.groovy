package com.uem.app


import static org.springframework.http.HttpMethod.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArquivoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Arquivo.list(params), model:[arquivoInstanceCount: Arquivo.count()]
	}

	def show(Arquivo arquivoInstance) {
		respond arquivoInstance
	}

	def create() {
		respond new Arquivo(params)
	}

	@Transactional
	def save(Arquivo arquivoInstance) {

		def uploadedFile = request.getFile('file')
		if(!uploadedFile.empty){
			arquivoInstance.nomeArquivo = uploadedFile.name
			arquivoInstance.nomeOriginal = uploadedFile.originalFilename
			arquivoInstance.tamanhoArquivo = uploadedFile.size
			arquivoInstance.contentType = uploadedFile.contentType
		}

		def webRootDir = servletContext.getRealPath("/")
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

		if (arquivoInstance == null) {
			notFound()
			return
		}

		if (arquivoInstance.hasErrors()) {
			respond arquivoInstance.errors, view:'create'
			return
		}

		arquivoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'arquivoInstance.label', default: 'Arquivo'),
					arquivoInstance.id
				])
				redirect arquivoInstance
			}
			'*' { respond arquivoInstance, [status: CREATED] }
		}
	}

	def edit(Arquivo arquivoInstance) {
		respond arquivoInstance
	}

	@Transactional
	def update(Arquivo arquivoInstance) {

		def uploadedFile = request.getFile('file')
		if(!uploadedFile.empty){
			arquivoInstance.nomeArquivo = uploadedFile.name
			arquivoInstance.nomeOriginal = uploadedFile.originalFilename
			arquivoInstance.tamanhoArquivo = uploadedFile.size
			arquivoInstance.contentType = uploadedFile.contentType
		}

		def webRootDir = servletContext.getRealPath("/")
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

		if (arquivoInstance == null) {
			notFound()
			return
		}

		if (arquivoInstance.hasErrors()) {
			respond arquivoInstance.errors, view:'edit'
			return
		}

		arquivoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Arquivo.label', default: 'Arquivo'),
					arquivoInstance.id
				])
				redirect arquivoInstance
			}
			'*'{ respond arquivoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Arquivo arquivoInstance) {

		if (arquivoInstance == null) {
			notFound()
			return
		}

		arquivoInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Arquivo.label', default: 'Arquivo'),
					arquivoInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'arquivoInstance.label', default: 'Arquivo'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	def getArquivo() {
		def resposta = [error: false]
		
		println params

		try {
			// Exemplo de id: B01_050_150_900_649_801_400_112_199_120_510
			def idDecs = params.id.replace('_', '.')
			def descritor = Descritor.findByIdDecs(idDecs)

			def aqvs = Arquivo.executeQuery("select distinct a from Arquivo as a join a.descritores as d where d = :d", [d: descritor])
			Arquivo arquivoInstance = aqvs.first();

			File file = new File(arquivoInstance.caminhoRelativo)

			resposta.filebytes = file.getBytes()
			resposta.arquivo = arquivoInstance

		} catch (Exception e) {
			resposta.error = true
			e.printStackTrace()
		}
		
		withFormat {
			json { render resposta as JSON }
			xml { render resposta as XML }
		}
	}


	/* se for responder para fazer download		
	 response.setHeader("Content-Type", "application/octet-stream;")
	 response.setHeader("Content-Disposition", "attachment;filename=\"${arquivoInstance.nomeOriginal}\"")
	 response.setHeader("Content-Length", "${file.size()}")
	 response.outputStream << file.bytes
	 */
}
