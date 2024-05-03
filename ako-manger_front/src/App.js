import './App.css';
import ChattingScreen from './pages/ChattingScreen';
import CreditScreen from './pages/CreditScreen';
import LoginScreen from './pages/LoginScreen';
import SignUpScreen from './pages/SignUpScreen';
import Home from './pages/home';

function App() {
  return (
    <div className="App">
      {/* 아래 괄호에 보고 싶은 페이지 이름으로 바꾸면 그 페이지를 볼 수 있습니다. */}
      <CreditScreen></CreditScreen>
    </div>
  );
}

export default App;
