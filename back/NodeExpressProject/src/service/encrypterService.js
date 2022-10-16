
import bcrypt from "bcryptjs"
export const encrypter = async (password) => {
    const salt = await bcrypt.genSalt(10);
    const hashPassword = await bcrypt.hash(password, salt)
    return hashPassword
}

export const comparator = async (passwordRequest, userPassword) => {
    return await bcrypt.compare(passwordRequest, userPassword);
}