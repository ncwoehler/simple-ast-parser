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
        })
    }
}

function transformObject(obj, key) {
    if (typeof obj === 'object') {
        const objectKeys = Object.keys(obj);
        return {
            text: {name: key},
            children: objectKeys.map(function (key) {
                return transformObject(obj[key], key);
            })
        }
    } else {
        return {
            text: {name: obj}
        }
    }
}

// FROM https://codepen.io/Macavity/pen/KQNWdg?editors=0100
const app = new Vue({
    el: '#editor',
    data() {
        return {
            query: `SELECT id, name, address FROM users WHERE is_customer IS NOT NULL ORDER BY created;`
        };
    },

    computed: {
        escapedQuery() {
            return this.query.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
        }
    },


    watch: {
        query() {
            this.highlightSyntax();
        }
    },

    mounted() {
        this.highlightSyntax();
    },

    methods: {
        highlightSyntax() {
            $('code').html(this.escapedQuery);

            $('.syntax-highlight').each(function (i, block) {
                hljs.highlightBlock(block);
            });
        },

        escapeHtml(unsafe) {
            return unsafe.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
        }
    }
});