import { Router } from "express"
import { testController } from "./testController.js";
import { userRoutes } from "./userController.js";

const routes = Router()

userRoutes(routes)
testController(routes)

export default routes;
