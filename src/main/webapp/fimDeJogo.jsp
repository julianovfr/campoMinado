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
	<p>Você acertou a bomba!!!</p>
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
    
        
        <form id="send-id-form" action="/campo/iniciarJogo" method="post">
        <input type="submit" id="cell-id" name="id" value="Deseja iniciar um novo jogo?">
    	</form>
    </div>
    
   
</body>
</html>