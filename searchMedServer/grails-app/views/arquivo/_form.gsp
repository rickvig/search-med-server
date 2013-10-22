<%@ page import="com.uem.app.Arquivo" %>

<div class="fieldcontain">
	<label for="arquivo"> 
		<g:message code="arquivo.arquivo.label" default="Arquivo" />
	</label>
	<input type="file" id="file" name="file" />
</div>

<div class="fieldcontain ${hasErrors(bean: arquivoInstance, field: 'descritores', 'error')} ">
	<label for="descritores">
		<g:message code="arquivo.descritores.label" default="Descritores" />
		
	</label>
	<g:select name="descritores" from="${com.uem.app.Descritor.list()}" multiple="multiple" optionKey="id" size="5" value="${arquivoInstance?.descritores*.id}" class="many-to-many"/>
</div>

