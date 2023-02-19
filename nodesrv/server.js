const http = require('http');
const port = 8001;

const server = http.createServer((request, response) => {
    console.log('Request accepted: ' + request.url);
    const resString = '>>> Simple server running on Node.js ' + new Date().toLocaleString() + '\n';
    console.log('Response sending: ' + resString);
    response.end(resString);
});

server.listen(port, (err) => {
    if (err) {
        return console.log('something bad happened', err);
    }
    console.log(`server is listening on ${port}`);
});
