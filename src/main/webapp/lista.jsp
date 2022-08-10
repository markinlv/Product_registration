<%@ page import="java.util.List" %>
<%@ page import="com.example.Aula04.domain.Produto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Produto> listaProdutos = (List<Produto>) request.getAttribute("listaProdutos");
    if(listaProdutos == null){
        response.sendRedirect("produtos");
    }else{
%>
<html>
<head>
    <title>Lista de Produtos</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
        }

        thead tr {
            border-bottom: solid 3px #444;
        }

        tbody tr:hover {
            background-color: #c9a4a4;
        }
    </style>
</head>
<body>
<h4>Lista de Produtos</h4>
<a href="cadastro.jsp">+ Cadastrar Novo</a>
<table>
    <thead>
    <tr>
        <th>Código</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th colspan="2">Ações</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(Produto produto : listaProdutos){
    %>
    <tr>
        <td><%=produto.getCodigo() %></td>
        <td><%=produto.getDescricao() %></td>
        <td><%=produto.getPreco() %></td>
        <td><a href="produtos?acao=editar&codigo=<%=produto.getCodigo()%>">Editar</a></td>
        <td><a href="produtos?acao=excluir&codigo=<%=produto.getCodigo()%>" >Excluir</a></td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
<%
    }
%>