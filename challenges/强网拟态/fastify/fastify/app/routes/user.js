const merge = require('../utils/merge')
const bin = "/bin/bash"
const ChildProcess = require('child_process');

function checkUser(command){
    if (Array.isArray(command) === false || command.length > 2) {
        return false;
    }
    for (let i = 0; i < command.length; i++) {
        let cmd = command[i];
        if (typeof cmd !== 'string' || cmd.length > 4 || RegExp(/^[^a-zA-Z0-9-]+$/).test(command[i])) {
            return false;
        }
    }
    return true;
}

async function routes (fastify, options) {
    fastify.route(
        {
            method: 'POST',
            url: '/user',
            schema: {
                querystring: {
                    user: { type: 'string' },
                },
                additionalProperties: false,
                response: {
                    200: {
                        $ref: 'respWrapper#/response/success'
                    }
                }
            },
            preHandler: function (request, reply, done) {
                //user init
                request.user = {username : 'guest', command: ["-c", "id"]}
                let user = JSON.parse(request.body.user)
                // clean user command
                if (checkUser(user.command) !== true) {
                    user.command = ["-c", "id"]
                }
                try {
                    merge(request.user, user)
                }catch (e){
                    reply.code(400).send({status: 1, info: "Something error"})
                    return ;
                }
                done()
            },
            handler : function (request, reply) {
                ChildProcess.execFile(bin, request.user.command, (error, stdout, stderr) => {
                    if (error) {
                        reply.code(400).send({status: 1, info: error})
                    }
                    reply.code(200).send({ status : 0 , info : `User of ${request.user.username} : ${stdout}`});
                });
            }
        })
    fastify.route({
        method: 'GET',
        url: '/',
        response: {
            $ref: 'respWrapper#/response/success'
        },
        handler: function (request, reply) {
            reply.send({ status: 0, info: 'go user' })
        }
    })
}

module.exports = routes