import React, { useState } from "react";
import Ako_sit from "../pages/assets/ako-sit.png";
import "../css/SignUp_first.css";
import { useNavigate } from "react-router-dom";
import HeaderComponent from'../pages/components/HeaderComponent';

const SignUp_first = () => {
  const navigate = useNavigate();

  const gotoSignUpScreen = () => {
    navigate("/signup");
  };
  const gotoLoginScreen = () => {
    navigate("/login");
  };

  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [isSignUpSuccess, setIsSignUpSuccess] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
      return;
    }

    try {
      const response = await fetch("/users/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: userId,
          password: password,
        }),
      });

      if (response.ok) {
        setIsSignUpSuccess(true);
      } else {
        const errorData = await response.json();
        setError(`회원가입 중 오류가 발생했습니다: ${errorData.message}`);
      }
    } catch (error) {
      setError(`회원가입 중 오류가 발생했습니다: ${error.message}`);
    }
  };

  return (
    <div className="signup-container">
      <HeaderComponent/>
      <img className="home-image" src={Ako_sit} alt="LoginScreen" />
      {isSignUpSuccess && (
        <div className="signup-success-overlay">
        <div className="signup-success-message">
          <p>회원가입 성공!</p>
          <p>추가 정보를 입력하러 가시겠습니까?</p>
          <button className="add-info-button" onClick={gotoLoginScreen}>
            No
          </button>
          <button className="add-info-button" onClick={gotoSignUpScreen}>
            Yes!
          </button>
          </div>
        </div>
      )}
      {!isSignUpSuccess && (
        <form onSubmit={handleSubmit} className="signup-form">
          <input
            type="text"
            placeholder="아이디"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            className="input-field"
          />
          <input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="input-field"
          />
          <input
            type="password"
            placeholder="비밀번호 확인"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            className="input-field"
          />
          <button type="submit" className="signup-button">
            회원가입
          </button>
        </form>
      )}
    </div>
  );
};

export default SignUp_first;
