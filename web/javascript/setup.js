$(function () {
    $.getJSON("list", function (data) {
        var basePath = data["base_path"];
        var input = list(data["root_items"]);
        $("#fs-tree").append(input).jstree(
            { "themes": {
                    "theme": "default",
                    "dots": false,
                    "icons": false
                }}).bind("ready.jstree", function (event, data) {
            $(this).jstree("open_all");
        }).on('select_node.jstree', function(e, data){

            if (data.node.children.length !== 0) {
                return;
            }
            // 選択したファイルのパス
            var path = data.node.data.jstree.id;

            $("<div>")
                .append($("<div>").text("選択されたＩＦファイル：").append("<br>").append($("<span class='fs-path'>").text(basePath + path)))
                .append("<br>")
                .append($("<div>")
                    .append($("<input type='radio' name='file_type' value='1' id='original_file' style='height: 14px; vertical-align: text-bottom'> "))
                    .append($("<img src='img/binary-40.png' style='height: 16px;vertical-align: text-bottom'>"))
                    .append($("<label>").attr("for", "original_file").text("オリジナルファイル")).append("<br/>"))
                .append($("<div>")
                    .append($("<input type='radio' name='file_type' value='2' id='excel_file' style='height: 14px; vertical-align: text-bottom'> "))
                    .append($("<img src='img/ms-excel-48.png' style='height: 16px;vertical-align: text-bottom'>"))
                    .append($("<label>").attr("for", "excel_file").text("分割済みエクセルファイル")).append("<br/>")
                )
                .attr("title", "ダウンロード確認").dialog({
                    autoOpen: true,
                    closeOnEscape: true,
                    resizable: false,
                    modal: true,
                    buttons: [{
                        text: "ダウンロード",
                        click: function () {

                            var uploadPath = path;
                            if (uploadPath.startsWith("/")) {
                                uploadPath = uploadPath.substr(1);
                            }
                            window.location.href = "download?path=" + encodeURI(uploadPath);


                            $(this).dialog( "destroy" );
                        }
                    }],
                    close: function () {
                        $(this).dialog( "destroy" );
                    }
            });
        });
    });
});

function list(data) {
    var items = "<ul>";

    $.each(data, function(key, fileitem) {

        if (fileitem["type"] === "file") {
            items += '<li data-jstree=\'{ "icon" : "jstree-file", "id": "' + fileitem["relativePath"].replace(/\\/g,"\\\\")  +'"}\'><span class="last-modified-datetime">' + fileitem["lastModified"] + '</span> <b>' + fileitem["name"] + '</b></li>';
        } else if (fileitem["type"] === "directory") {
            items += '<li>' + fileitem["name"] + list(fileitem["sub"]) + '</li>';
        }
    });

    items += "</ul>";
    return items;
}