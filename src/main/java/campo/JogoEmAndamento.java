package campo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import campo.LogicaJogo;

@WebServlet("/jogoEmAndamento")
public class JogoEmAndamento extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException  {
		HttpSession session = req.getSession();
		//System.out.println(session);
		LogicaJogo jogo = (LogicaJogo) session.getAttribute("meuJogo");
		String click = req.getParameter("id");
		if(jogo.jogada(click)) {
			if(jogo.totalFalta()) {
				//jogo.mostrarBombas(click);
				RequestDispatcher rd = req.getRequestDispatcher("/venceu.jsp"); //o rd deve saber o tipo de variável
				req.setAttribute("tabuleiro", jogo.getTabuleiro()); //pindura a requisição
				rd.forward(req, resp); //leva a requisição e a resposta para ser executada

				if (session != null) {
				    session.invalidate(); // Invalida (encerra) a sessão
				}
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("/jogo.jsp"); //o rd deve saber o tipo de variável
				req.setAttribute("tabuleiro", jogo.getTabuleiro()); //pindura a requisição
				rd.forward(req, resp); //leva a requisição e a resposta para ser executada
			}
		}
		else {
			//criar um parametro para mostrar todas as bombas no tabuleiro
			jogo.mostrarBombas(click);
			RequestDispatcher rd = req.getRequestDispatcher("/fimDeJogo.jsp"); //o rd deve saber o tipo de variável
			req.setAttribute("tabuleiro", jogo.getTabuleiro()); //pindura a requisição
			rd.forward(req, resp); //leva a requisição e a resposta para ser executada
			
			if (session != null) {
			    session.invalidate(); // Invalida (encerra) a sessão
			}
		}
	}
}
