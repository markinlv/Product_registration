<%@ page import="com.example.Aula04.domain.Produto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Produto produtoEditar = (Produto) request.getAttribute("produtoEditar");
    String codigo = "";
    String descricao="";
    String preco= "";
    if(produtoEditar != null){
        codigo= produtoEditar.getCodigo().toString();
        descricao=produtoEditar.getDescricao();
        preco=produtoEditar.getPreco().toString();
    }

%>
<html>
<head>
    <title>Cadastro de Produto</title>
    <style>
        .campoFormulario {
            margin: 10px;
        }
    </style>
</head>
<body>
<h4>Cadastrar Produto</h4>

<form action="produtos" method="post" >
    <% if (produtoEditar != null){%>
    <input type="hidden" name="codigoEditar" value="<%=codigo%>" >
    <% } %>
    <div class="campoFormulario">
        <label>Código: </label>
        <input type="number" name="codigo" value="<%=codigo%>" required />
    </div>
    <div class="campoFormulario">
        <label>Descrição: </label>
        <input type="text" name="descricao"  value="<%=descricao%>"required />
    </div>
    <div class="campoFormulario">
        <label>Preço: </label>
        <input type="number" name="preco"  value="<%=preco%>" required />
    </div>
    <div class="campoFormulario">
        <input type="submit" value="Enviar cadastro" />
    </div>
</form>

</body>
</html>
