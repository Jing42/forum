$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	var title = $("#recipient-name").val();
    var content = $("#message-text").val();

    $.post(
        CONTEXT_PATH + "/discuss/add",
        {"title":title,"content":content},
        function(data) {
            data = $.parseJSON(data);
            $("#hintBody").text(data.msg);
            $("#hintModal").modal("show");
                // 2秒后,自动隐藏提示框
                setTimeout(function(){
                    $("#hintModal").modal("hide");
                    // 刷新页面
                    if(data.code == 0) {
                        window.location.reload();
                    }
                }, 2000);
        }
    );


}