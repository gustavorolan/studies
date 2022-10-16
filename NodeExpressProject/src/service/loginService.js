import { comparator } from "./encrypterService.js";
import { verifyIfUserDoesNotExists } from "./validationService.js";
import { UserModel } from "../models/User.js"
import jwt from "jsonwebtoken"

export const login = async (body, res) => {

    const isEmailNotInUse = await verifyIfUserDoesNotExists(body.email);
    if (!!isEmailNotInUse) return res.status(400).send(isEmailNotInUse)

    const user = await UserModel.findOne({
        email: body.email
    })

    const validPass = await comparator(body.password, user.password)
    if (!validPass) return res.status(400).send("Email or password is wrong")

    const token = jwt.sign({
        _id: user._id
    }, process.env.TOKEN_SECRET)

    res.header('auth-token', token).send("logged")

}