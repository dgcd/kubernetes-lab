<template>
	<dir>
		<h3>Kubelab app</h3>
	    <input v-model="name" placeholder="enter name" @keydown.enter="onSend()">
	    <button v-on:click="onSend">Send</button>
	    <br><br>

        <table v-if="responses.length" cellspacing="2" border="1" cellpadding="5">
            <tr>
                <th>name</th>
                <th>id</th>
                <th>gwName</th>
                <th>gwCntr</th>
                <th>bkName</th>
                <th>bkCntr</th>
                <th>created</th>
            </tr>
            <tr v-for="r in responses" :key="r.id">
                <td>{{r.requestName}}</td>
                <td>{{r.requestId}}</td>
                <td>{{r.gatewayName}}</td>
                <td>{{r.gatewayCounter}}</td>
                <td>{{r.backendName}}</td>
                <td>{{r.backendCounter}}</td>
                <td>{{r.responseCreated}}</td>
            </tr>
        </table>
	</dir>
</template>

<script>
	export default {
		data() {
			return {
				responses: [],
				name: '',
				counter: 1
			}
		},
	  	methods: {
            onSend() {
				if (this.name.trim()) {
					fetch("/api", {
						method: "POST",
						headers: { 'Content-Type': 'application/json;charset=UTF-8' },
						body: JSON.stringify( { 
							requestName: this.name.trim(),
							requestId: this.counter,
						} )
					})
					.then((response) => response.json())
					.then((newResponse) => {
						console.log('response: ', newResponse);
						this.responses = [newResponse, ...this.responses];
					});
				}
				this.name = '';
				this.counter = this.counter + 1;
            }
        },
	}
</script>
