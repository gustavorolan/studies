import { UserModel } from "../models/User.js"
import { encrypter } from "./encrypterService.js";
import { userValidationRequest, verifyIfUserAlreadyExists } from "./validationService.js"

export const createNewUser = async (body, res) => {

    const userValidation = userValidationRequest(body);
    if (!!userValidation) return res.status(400).send(userValidation)

    const isEmailInUse = await verifyIfUserAlreadyExists(body.email)
    if (!!isEmailInUse) return res.status(400).send(isEmailInUse)

    const passwordEncrypted = await encrypter(body.password)

    const user = new UserModel({
        username: body.username,
        password: passwordEncrypted,
        email: body.email
    })

    try {
        const {_id} = await user.save()
        return res.send(_id)
    }
    catch (e) {
        return res.status(400).send(e)
    }
}
