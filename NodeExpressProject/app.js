import express from 'express';
import morgan from 'morgan';
import routes from './src/controller/index.js';
import mongoose from "mongoose"
import dotenv from "dotenv"
import bodyParser from 'body-parser';
import { auth } from './src/service/verifyToken.js';

const app = express();

dotenv.config()

app.use(morgan('dev'))

app.use(bodyParser.json())

app.use(routes)

mongoose.connect(process.env.DB_CONNECTION, () => console.log("conected to db"))

app.listen(3000, console.log("listen to 3000"));
