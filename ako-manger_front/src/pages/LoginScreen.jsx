import React, { useState } from 'react';
import '../css/LoginScreen.css';
import Ako_sit from '../pages/assets/ako-sit.png';

// https://goddaehee.tistory.com/305

const LoginScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // 여기서 로그인 로직을 구현합니다.
    console.log('Username:', username);
    console.log('Password:', password);
  };

  return (
    <div className="login-container">
      <h1 className="login-title">Hello, A-Ko!</h1>
      <img className="home-image" src={Ako_sit} alt="LoginScreen" />
      <form className="login-form" onSubmit={handleSubmit}>
        <input
          className="login-input"
          type="text"
          placeholder="아이디"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          className="login-input"
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="login-button" type="submit">로그인</button> 
        <div className="login-options">
          <span className="login-option">회원가입</span>
          <span className="login-option">아이디찾기</span>
          <span className="login-option">비밀번호 찾기</span>
        </div>
        <button className="login-button-kakao" type="submit">카카오 로그인</button>
      </form>
    </div>
  );
};

export default LoginScreen;