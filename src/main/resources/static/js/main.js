$(document).ready(function () {

    $("#parse-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fireAjaxSubmit();

    });

});

function fireAjaxSubmit() {

    var request = {}
    request["sql"] = $("#sqlInput").val();

    $("#btn-parse").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/parse",
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {

            console.log("SUCCESS : ", response);
            $("#btn-parse").prop("disabled", false);

            simple_chart_config = {
                chart: {
                    container: "#tree-simple"
                },

                nodeStructure: transformData(response.data)
            };

            var my_chart = new Treant(simple_chart_config, null, $);

            $('#feedback').html("<span></span>");
        },
        error: function (e) {

            var json =
                "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                e.responseJSON.reason +
                "</div>";
            $('#feedback').html(json);
            $('#tree-simple').html("<div></div>");

            console.log("ERROR : ", e);
            $("#btn-parse").prop("disabled", false);

        }
    });

}

function transformData(data) {
    return {
        text: {name: "Statements"},
        children: data.statements.map(transformStatements)
    };
}

function transformStatements(obj) {
    const objectKeys = Object.keys(obj);
    return {
        text: {name: obj.type},
        children: objectKeys.filter(function (value) {
            return value !== "type"
        }).map(function (key) {
            return transformObject(obj[key], key);
        }).filter(function (value) {
            return value !== null
        })
    }
}

function transformObject(obj, key) {
    if (obj && typeof obj === 'object') {
        const objectKeys = Object.keys(obj);
        return {
            text: {name: key},
            children: objectKeys.map(function (key) {
                return transformObject(obj[key], key);
            })
        }
    }  else if(obj) {
        return {
            text: {name: obj}
        }
    } else {
        return null
    }
}