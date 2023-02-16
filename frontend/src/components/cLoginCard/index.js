import './style.css';
import logo from "../../assets/img/iLogo.png";

function cLoginCard() {
    return (
        <div className="box">
            <div className="ImgContainer">
                <img src={logo} alt="logo.png"/>
            </div>
            <div className="ContentContainer">
                <div id="EmailContainer">
                    <label for="Email">Email</label>   
                    <br/>
                    <input type="email" class="Inputs"></input>
                </div>
                <div id="PwdContainer">
                    <label for="Pwd">Senha</label>
                    <br/>
                    <input type="password" class="Inputs"></input> 
                </div>
                <button id="LoginButton">Log in</button>
                <p>Don’t have an account? <a href="https://www.youtube.com/watch?v=C_339sQXq9Y">Sign Up</a></p>
            </div>
        </div>
    );
  }
  
  export default cLoginCard;
  