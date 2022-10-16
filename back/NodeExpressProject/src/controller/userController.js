import { createNewUser } from "../service/createNewUserService.js"
import { login } from "../service/loginService.js"


export const userRoutes = (routes) => {

    const route = "/user"

    routes.post(route + '/create', ({ body }, res) => {
        createNewUser(body, res)
    })

    routes.post(route + '/login', ({ body }, res) => {
        login(body, res)
    })
}