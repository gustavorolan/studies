import { auth } from "../service/verifyToken.js"

export const testController = (routes) => {
    const route = "/test"
    routes.get(route + '/hello', auth, ({ body }, res) => {
        res.send("hello")
    })
}