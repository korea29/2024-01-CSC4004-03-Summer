import React, { useState } from "react";
import "../css/LoginScreen.css";
import Ako_sit from "../pages/assets/ako-sit.png";
import { useNavigate } from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent";

// https://goddaehee.tistory.com/305 페이지 연결

const LoginScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // 더미 데이터를 사용하여 사용자 인증을 시뮬레이션합니다.
      const response = await fetch("/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        // 로그인 성공 시 로직을 여기에 작성합니다.
        console.log("로그인 성공!");
      } else {
        // 로그인 실패 시 로직을 여기에 작성합니다.
        console.error("로그인 실패");
        alert("아이디 또는 비밀번호가 올바르지 않습니다.");
      }
    } catch (error) {
      console.error("로그인 요청 중 오류 발생:", error);
      alert("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
    }
  };
  const navigate = useNavigate();

  const gotoSign = () => {
    navigate("/signup_first");
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
        <button className="login-button" type="submit">
          로그인
        </button>
        <div className="login-options">
          <span className="login-option" onClick={gotoSign}>
            회원가입
          </span>
          <span className="login-option">아이디찾기</span>
          <span className="login-option">비밀번호 찾기</span>
        </div>
        <button className="login-button-kakao" type="submit">
          카카오 로그인
        </button>
      </form>
    </div>
  );
};

export default LoginScreen;
