import React from 'react';
import '../css/Home.css';
import Ako_sit from '../pages/assets/ako-sit.png';


const Home = () => {
  return (
    <div className="home-container">
      {/* 테스트 */}
      <h1 className="home-title">Hello, A-Ko!</h1>
      <img className="home-image" src={Ako_sit} alt="Home" />
      <div className="home-chat">
      <input
          type="text"
          placeholder="안녕, 오랜만이야. 요즘 학교 생활은 어때?"
          className="home-chat-input"
        />
        <button className="home-chat-button">start chat</button>
      </div>
      <div className="home-options">
        <div className="home-option">
          <button className="home-option-icon">
          </button>
          <span className="home-option-text">ID's 취득 학점</span>
        </div>
        <div className="home-option">
          <button className="home-option-icon">
          </button>
          <span className="home-option-text">YY %학기 시간표</span>
        </div>
        <div className="home-option">
          <button className="home-option-icon">
          </button>
          <span className="home-option-text">ID's 정보 수정</span>
        </div>
      </div>
    </div>
  );
};

export default Home;