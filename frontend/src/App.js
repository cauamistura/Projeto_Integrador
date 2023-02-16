import './App.css';
//import cLoginCard from './components/cLoginCard';
import logo from './assets/img/iLogo.png'
import { useState } from 'react';

function App() {

  const [WEmail, setWEmail] = useState("");
  const [WPwd, setWPwd]     = useState(""); 
  return (
      <>
        <div className="container">
          <div className="box">
              <div className="ImgContainer">
                  <img src={logo} alt="logo.png"/>
              </div>
              <div className="ContentContainer">
                  <div id="EmailContainer">
                      <label for="Email">Email</label>   
                      <br/>
                      <input 
                            type="email"
                            value={WEmail}
                            onChange={(e) => setWEmail(e.target.value)}
                            className="Inputs"
                      ></input>
                  </div>
                  <div id="PwdContainer">
                      <label for="Pwd">Senha</label>
                      <br/>
                      <input 
                            type="password" 
                            value={WPwd}
                            onChange={(e) => setWPwd(e.target.value)}                      
                            class="Inputs"></input> 
                  </div>
                  <button id="LoginButton">Log in</button>
                  <p>Don’t have an account? <a href="https://www.youtube.com/watch?v=C_339sQXq9Y">Sign Up</a></p>
              </div>
          </div>
        </div>
      </>
  );
}
export default App;
