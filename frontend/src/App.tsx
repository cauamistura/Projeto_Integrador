import './index.css';

function App() {
  return (
    <div className="container">
        <div className="box">
            <div className="ImgContainer">
                <img src={} alt="iLogo.png">
            </div>
            <div className="ContentContainer">
                <div id="EmailContainer">
                    <label>Email</label>
                    <br>
                    <input type="email" className="Inputs">
                </div>
                <div id="PwdContainer">
                    <label>Senha</label>
                    <br>
                    <input type="password" className="Inputs"> 
                </div>
                <button id="LoginButton">Log in</button>
                <p>Don’t have an account? <a href="">Sign Up</a></p>
            </div>
        </div>
    </div>    
  )
}

export default App;
