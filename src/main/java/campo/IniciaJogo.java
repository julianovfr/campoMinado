package campo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/iniciarJogo")
public class IniciaJogo  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException  {
		HttpSession session = req.getSession();
		LogicaJogo jogo = new LogicaJogo();
		RequestDispatcher rd = req.getRequestDispatcher("/jogo.jsp"); //o rd deve saber o tipo de variável
		session.setAttribute("meuJogo", jogo);
		req.setAttribute("tabuleiro", jogo.getTabuleiro()); //pindura a requisição
		rd.forward(req, resp); //leva a requisição e a resposta para ser executada
	}
}
