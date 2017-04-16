function compareArray(array , element){
    var counter = 0;
    for(var i=1;i<array.length-1;i++){
        for(var j=i+1;j<array.length;j++){
            if(array[i] == array[j] && array[i] == element){
                counter++
            }
        }
    }
    if(array.length == counter)
        return true;
    else 
        return false;
}

function playComputer(){
    var first = Math.floor((Math.random() * 3) + 1);
    var second = Math.floor((Math.random() * 3) + 1);
    if($("#"+first+"x"+second).text() != ""){
        playComputer();
    }else{
        $("#" + first+"x"+second).trigger("click");
    }
}

function resetTheGame(){
    $("#game-table td").click(function(){
        $("#game-table td").empty();
        $("#game-steps").empty();
        $("#game-table td").css("background-color" , "white");
        hitCounter = 0;
        window.location.reload();
    });
}

$(document).ready(function(){
    var hitCounter = 0;
    var gameIsEnd;
    var winSnd = new Audio("resources/sound/win.mp3"); // buffers automatically when created
    var loseSnd = new Audio("resources/sound/lose.mp3"); // buffers automatically when created
    var clickedOneMoreTime = false;
    for(var i=1;i<=3;i++){
        for(var j=1;j<=3;j++){
            $("#"+i+"x"+j).click(function(event){
                var text = $(this).text();
                if(text.length==0){
                    var row = $(this).attr("row");
                    var column = $(this).attr("column");
                    if(hitCounter % 2 == 0){
                        $(this).text("X");
                        $(this).css("font-weight" , "bold");
                        $("#game-steps").append("<b>" + (hitCounter+1) + ". </b>" + "You put the X to " + row + ". row , " + column + ". column" + "<br>");
                        if($("#1x1").text() == "X" && $("#1x2").text() == "X" && $("#1x3").text() == "X"){
                            $("#1").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#2x1").text() == "X" && $("#2x2").text() == "X" && $("#2x3").text() == "X"){
                            $("#2").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#3x1").text() == "X" && $("#3x2").text() == "X" && $("#3x3").text() == "X"){
                            $("#3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x3").text() == "X" && $("#2x3").text() == "X" && $("#3x3").text() == "X"){
                            $("#1x3").css("background-color" , "red");
                            $("#2x3").css("background-color" , "red");
                            $("#3x3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x2").text() == "X" && $("#2x2").text() == "X" && $("#3x2").text() == "X"){
                            $("#1x2").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x2").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x1").text() ==  "X" && $("#2x1").text() == "X" && $("#3x1").text() == "X"){
                            $("#1x1").css("background-color" , "red");
                            $("#2x1").css("background-color" , "red");
                            $("#3x1").css("background-color" , "red")
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x1").text() ==  "X" && $("#2x2").text() == "X" && $("#3x3").text() == "X"){
                            $("#1x1").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x3").text() ==  "X" && $("#2x2").text() == "X" && $("#3x1").text() == "X"){ 
                            $("#1x3").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x1").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if(gameIsEnd){
                            winSnd.play();
                            $("#game-steps").append("<br><mytext><strong>GAME IS OVER</strong>." + "<strong style='color:red;'> YOU WIN !</strong></mytext>");
                            return false;
                        }
                    }else{
                        $(this).text("O");
                        $(this).css("font-weight" , "bold");
                        $("#game-steps").append("<b>" + (hitCounter+1) + ". </b>" + "Computer put the O to " + row + ". row , " + column + ". column" + "<br>");
                        if($("#1x1").text() == "O" && $("#1x2").text() == "O" && $("#1x3").text() == "O"){
                            $("#1").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#2x1").text() == "O" && $("#2x2").text() == "O" && $("#2x3").text() == "O"){
                            $("#2").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;

                        }
                        if($("#3x1").text() == "O" && $("#3x2").text() == "O" && $("#3x3").text() == "O"){
                            $("#3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x3").text() == "O" && $("#2x3").text() == "O" && $("#3x3").text() == "O"){
                            $("#1x3").css("background-color" , "red");
                            $("#2x3").css("background-color" , "red");
                            $("#3x3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x2").text() == "O" && $("#2x2").text() == "O" && $("#3x2").text() == "O"){
                            $("#1x2").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x2").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x1").text() ==  "O" && $("#2x1").text() == "O" && $("#3x1").text() == "O"){
                            $("#1x1").css("background-color" , "red");
                            $("#2x1").css("background-color" , "red");
                            $("#3x1").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x1").text() ==  "O" && $("#2x2").text() == "O" && $("#3x3").text() == "O"){
                            $("#1x1").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x3").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if($("#1x3").text() ==  "O" && $("#2x2").text() == "O" && $("#3x1").text() == "O"){
                            $("#1x3").css("background-color" , "red");
                            $("#2x2").css("background-color" , "red");
                            $("#3x1").css("background-color" , "red");
                            resetTheGame();
                            gameIsEnd = true;
                        }
                        if(gameIsEnd){
                            loseSnd.play();
                            $("#game-steps").append("<br><mytext><strong>GAME IS OVER</strong>." + "<strong style='color:red;'> COMPUTER WIN !</strong></mytext>");
                            return false;
                        }
                    }
                    hitCounter++;
                    if(hitCounter == 9){
                        resetTheGame();
                    }
                }else{
                    clickedOneMoreTime = true;
                }
            }).on('mouseup',function(){
                setTimeout(function(){
                    if(gameIsEnd !=true && !clickedOneMoreTime){
                        playComputer(); 
                    }
                },500);
                clickedOneMoreTime = false;
            });
        }
    }
});