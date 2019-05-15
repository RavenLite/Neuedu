new Vue({
    el: '#app',
    data: {
        url: 'http://localhost:8080/login',
        username: "",
        password: ""
    },
    methods: {
        login(){
            console.log(this.username + this.password);
            this.$http.get(this.url,{params: {username: this.username, password: this.password}}).then((response) => {
                console.log(response)
            })
        }
    }
});