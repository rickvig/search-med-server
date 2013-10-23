
<%@ page import="com.uem.app.Arquivo"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'arquivo.label', default: 'Arquivo')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-arquivo" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="list-arquivo" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>

					<g:sortableColumn property="nomeArquivo"
						title="${message(code: 'arquivo.nomeArquivo.label', default: 'Nome Arquivo')}" />

					<g:sortableColumn property="nomeOriginal"
						title="${message(code: 'arquivo.nomeOriginal.label', default: 'Nome Original')}" />
					
					<g:sortableColumn property="descritores"
                        title="${message(code: 'descritor.descritores.label', default: 'Descritores')}" />

					<g:sortableColumn property="tamanhoArquivo"
						title="${message(code: 'arquivo.tamanhoArquivo.label', default: 'Tamanho Arquivo')}" />

				</tr>
			</thead>
			<tbody>
				<g:each in="${arquivoInstanceList}" status="i" var="arquivoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${arquivoInstance.id}">
								${fieldValue(bean: arquivoInstance, field: "nomeArquivo")}
							</g:link></td>

						<td>
							${fieldValue(bean: arquivoInstance, field: "nomeOriginal")}
						</td>
						
                        <td>
                            ${fieldValue(bean: arquivoInstance, field: "descritores")}
                        </td>

						<td>
							${fieldValue(bean: arquivoInstance, field: "tamanhoArquivo")}
						</td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${arquivoInstanceCount ?: 0}" />
		</div>
	</div>
</body>
</html>
