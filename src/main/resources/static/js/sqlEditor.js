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