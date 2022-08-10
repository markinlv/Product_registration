package com.example.Aula04.servlets;
import com.example.Aula04.domain.Produto;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet(name = "ProdutoServlet", value = "/produtos")
public class ProdutoServlet extends HttpServlet {

    private List<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if(acao != null && acao.equals("excluir")){
            String codigo = request.getParameter("codigo");
            List<Produto>produtosEncontrados = listaProdutos
                    .stream()
                    .filter(produto -> produto.getCodigo().equals(Long.parseLong(codigo)))
                    .collect(Collectors.toList());
            Produto produtoRemover = produtosEncontrados.size() > 0 ? produtosEncontrados.get(0) : null;
            listaProdutos.remove(produtoRemover);
            response.sendRedirect("produtos");
        }else if(acao != null && acao.equals("editar")) {
            String codigo = request.getParameter("codigo");
            List<Produto>produtosEncontrados = listaProdutos
                    .stream()
                    .filter(produto -> produto.getCodigo().equals(Long.parseLong(codigo)))
                    .collect(Collectors.toList());
            Produto produtoEditar = produtosEncontrados.size() > 0 ? produtosEncontrados.get(0) : null;
            request.setAttribute("produtoEditar", produtoEditar);
            RequestDispatcher rq = request.getRequestDispatcher("cadastro.jsp");
            rq.forward(request, response);
        }else
            {
                request.setAttribute("listaProdutos", listaProdutos);
                RequestDispatcher rq = request.getRequestDispatcher("lista.jsp");
                rq.forward(request, response);
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigoEditar = request.getParameter("codigoEditar");
        String codigo = request.getParameter("codigo");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");


          Produto produto = null;
        if(codigoEditar != null ){
            List<Produto>produtosEncontrados = listaProdutos
                    .stream()
                    .filter(p -> p.getCodigo().equals(Long.parseLong(codigoEditar)))
                    .collect(Collectors.toList());
          produto = produtosEncontrados.size() > 0 ? produtosEncontrados.get(0) : null;

        }else {
            produto = new Produto();
        }

        produto.setCodigo(Long.parseLong(codigo));
        produto.setDescricao(descricao);
        produto.setPreco(Double.parseDouble(preco));

        if(codigoEditar == null ){
            listaProdutos.add(produto);
        }

       response.sendRedirect("produtos");
    }

}
