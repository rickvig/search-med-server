
<%@ page import="com.uem.app.Arquivo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'arquivo.label', default: 'Arquivo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-arquivo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-arquivo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list arquivo">
			
				<g:if test="${arquivoInstance?.nomeArquivo}">
				<li class="fieldcontain">
					<span id="nomeArquivo-label" class="property-label"><g:message code="arquivo.nomeArquivo.label" default="Nome Arquivo" /></span>
					
						<span class="property-value" aria-labelledby="nomeArquivo-label"><g:fieldValue bean="${arquivoInstance}" field="nomeArquivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${arquivoInstance?.nomeOriginal}">
				<li class="fieldcontain">
					<span id="nomeOriginal-label" class="property-label"><g:message code="arquivo.nomeOriginal.label" default="Nome Original" /></span>
					
						<span class="property-value" aria-labelledby="nomeOriginal-label"><g:fieldValue bean="${arquivoInstance}" field="nomeOriginal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${arquivoInstance?.caminhoRelativo}">
				<li class="fieldcontain">
					<span id="caminhoRelativo-label" class="property-label"><g:message code="arquivo.caminhoRelativo.label" default="Caminho Relativo" /></span>
					
						<span class="property-value" aria-labelledby="caminhoRelativo-label"><g:fieldValue bean="${arquivoInstance}" field="caminhoRelativo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${arquivoInstance?.tamanhoArquivo}">
				<li class="fieldcontain">
					<span id="tamanhoArquivo-label" class="property-label"><g:message code="arquivo.tamanhoArquivo.label" default="Tamanho Arquivo" /></span>
					
						<span class="property-value" aria-labelledby="tamanhoArquivo-label"><g:fieldValue bean="${arquivoInstance}" field="tamanhoArquivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${arquivoInstance?.descritores}">
				<li class="fieldcontain">
					<span id="descritores-label" class="property-label"><g:message code="arquivo.descritores.label" default="Descritores" /></span>
					
						<g:each in="${arquivoInstance.descritores}" var="d">
						<span class="property-value" aria-labelledby="descritores-label"><g:link controller="descritor" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:arquivoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${arquivoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
