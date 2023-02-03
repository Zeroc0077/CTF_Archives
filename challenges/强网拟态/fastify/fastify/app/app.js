const userRoutes = require('./routes/user')
const fastify = require('fastify')({
    logger: {
        level: 'error'
    }
})
const port = process.env.PORT || 3000
const host = process.env.HOST || "0.0.0.0"
const respWrapper = {
    $id: 'respWrapper',
    type: 'object',
    response : {
        success: {
            type: 'object',
            properties: {
                status : { type: 'number' },
                info: { type: 'string' },
            }
        }
    }
}

fastify.addSchema(respWrapper)
fastify.register(userRoutes)

fastify.listen({
    host, port
}, (err, address) => {
    if (err) {
        fastify.log.error(err)
        process.exit(1)
    }
    fastify.log.info(`server listening on ${address}`)
})