import Joi from "@hapi/joi"
import { UserModel } from "../models/User.js"

const userSchemaValidation = Joi.object({
    username: Joi.string().min(6).required(),
    email: Joi.string().min(6).required().email(),
    password: Joi.string().min(6).required(),
})

export const userValidationRequest = (body) => {

    const { error } = userSchemaValidation.validate(body)

    if (error) {
        const { details } = error
        const { message } = details[0]
        return message
    }

}

export const verifyIfUserAlreadyExists = async (email) => {

    const message = "Email is already in use"

    const user = await UserModel.findOne({
        email: email
    })

    if (user) return message

}

export const verifyIfUserDoesNotExists = async (email) => {

    const message = "Email or password is wrong"

    const user = await UserModel.findOne({
        email: email
    })

    if (!user) return message

}