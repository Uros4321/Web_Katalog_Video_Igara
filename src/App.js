import logo from './logo.svg';
import './App.css';
import Test from './testing/Test'

var Temp = {
  ime:"testname"
}
var Val = 1

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p className='text-3xl underline'> 
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        {Val===1 ? (<Test/>):(<p>{Temp.ime}</p>)}
        {true && (<p>This is for only true</p>)}
      </header>
    </div>
  );
}

export default App;
