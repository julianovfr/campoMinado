<% 
	String[][] tabuleiro = (String[][]) request.getAttribute("tabuleiro");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campo Minado </title>
    <style>
        .board {
            display: grid;
            grid-template-columns: repeat(8, 100px);
            gap: 2px;
            margin: 0 auto;
        }
        
        .cell {
            width: 100px;
            height: 100px;
            border: 1px solid black;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 24px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="board" id="tabuleiro">
    	<%
    	for (int i=0;i<8;i++){%>
    		<%for(int j=0;j<8;j++){%>
    			<%if(tabuleiro[i][j]=="x"){ %>
    			    <div class="cell" id="<%=i %>-<%=j %>" style="background-color: red;">x</div>
    				
    			<%}else if(tabuleiro[i][j] == "-"){%>
    				<div class="cell" id="<%=i %>-<%=j %>"></div>
    			<%}else{ %>
    				<div class="cell" id="<%=i %>-<%=j %>" style="background-color: #808080;"><%=tabuleiro[i][j] %></div>
    			<%}%>
   			<%}%>
 		 <%}%>
    
        
        <form id="send-id-form" action="/campo/jogoEmAndamento" method="post">
        <input type="hidden" id="cell-id" name="id" value="">
    </form>
    </div>
    
    <script>
        // Adiciona um evento de clique para todas as c�lulas
        var cells = document.querySelectorAll(".cell");
        cells.forEach(function(cell) {
            cell.addEventListener("click", function(event) {
                var clickedCellId = event.target.id; // Captura o ID do elemento clicado
                enviarRequisicaoParaServlet(clickedCellId);
            });
        });

        function enviarRequisicaoParaServlet(cellId) {
            var form = document.getElementById("send-id-form");
            var input = form.querySelector("#cell-id");

            input.value = cellId; // Define o valor do campo de entrada oculto
            form.submit(); // Envia o formul�rio com o ID como par�metro POST
        }
    </script>
</body>
</html>
