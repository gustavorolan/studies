import { useState } from 'react';
import './App.css';
import { AllRoutes } from './ui/components';
import { Toast } from './ui/components/toast/toast.component';

function App() {
  const [toast,setToast]= useState(null)
  return <>
   <AllRoutes setToast={setToast}/>
   {toast&&<Toast children={toast.message} emoji={toast.emoji} />}
  </>
 
}

export default App;
