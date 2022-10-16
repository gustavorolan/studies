import mongoose from "mongoose"

const UserSchema = mongoose.Schema({
    username: {
        type: String,
        required: true,
        min:1,
        max:255
    },
    password: {
        type: String,
        required: true,
        min:1,
        max:1024
    },
    email: {
        type: String,
        required: true,
        min:1,
        max:255
    }

})

export const UserModel = mongoose.model('User', UserSchema)