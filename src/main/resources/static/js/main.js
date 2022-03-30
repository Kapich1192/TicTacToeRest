var messageApi = Vue.resource('/gameplay{/id}');

Vue.component('message-row',{
props:['message','messages'],
template:'<div>' +
            '<i>({{ message.id }})</i> {{ message.text }} ' +
            '<span style="position: absolute; right: 0">'+
            '<input type="button" value="Run" @click="run"/>'+
            '<input type="button" value="Delete" @click="del" />'+
            '</span>'+
         '</div>',
    methods:{
        del: function(){
            messageApi.remove({id: this.message.id}).then(result => {
                if(result.ok){
                    this.messages.splice(this.messages.indexOf(this.message),1)
                }
            })
        },
        run: function(){
            messageApi.get({id: this.message.id}).then(result => {

            })
        }
    }
});

Vue.component('messages-list', {
    props:['messages'],
    template:   '<div style="position: relative; width: 300px;" >'+
                    '<message-row v-for="message in messages" :key="message.id" :message="message" :messages="messages" />'+
                '</div>',
    created: function(){
        messageApi.get().then(result =>
        result.json().then(data =>
            data.forEach(message => this.messages.push(message))
            )
        )
    }
});

var app = new Vue({
  el: '#app',
  template:'<messages-list :messages="messages" />',
  data: {
    messages: []
  }
});


Vue.component('refresh-list', {
    props:['messages'],
    template:   '<div>'+
                '<span style="position: absolute; right: 0">'+
                '<input type="button" value="Refresh" @click="refresh" />'+
                '</span>'+
                '</div>',
methods:{
        refresh: function(){
            messageApi.get().then(result => {

            })
        }
}
});

var appRefresh = new Vue({
  el: '#appRefresh',
  template:'<refresh-list />',
  data: {

  }
})