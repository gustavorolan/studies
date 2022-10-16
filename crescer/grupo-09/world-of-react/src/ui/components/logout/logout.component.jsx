import './logout.style.css'

import { useGlobalCharacter, useGlobalUser } from '../../../context'
import { useNavigate } from 'react-router-dom'

export const Logout = ({ children }) => {

  const [, setGlobalCharacter] = useGlobalCharacter()
  const [, setGlobalUser] = useGlobalUser()
  const navigate = useNavigate()

  const onClickLogout = () =>{
    setGlobalCharacter({})
    setGlobalUser({token:""})
    navigate('/login')
  }

  return(
    <button onClick={onClickLogout} className='logout__button'>
      {children}
    </button>
  )
}