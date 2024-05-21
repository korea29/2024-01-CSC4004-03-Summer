import React, { useState } from "react";
import "../css/Home.css";
import Ako_sit from "../pages/assets/ako-sit.png";
import { useNavigate } from "react-router-dom";
import "./components/TimeTableModal.css";

const Home = () => {
  const navigate = useNavigate();

  // 입력 값 상태 관리
  const [inputValue, setInputValue] = useState("");

  const gotoChat = () => {
    navigate("/chatting", { state: { message: inputValue } });
  };

  const gotoCredit = () => {
    navigate("/credit");
  };

  const gotoChange = () => {
    navigate("/signup");
  };

  // 모달 상태 관리
  const [modalVisible, setModalVisible] = useState(false);

  const openModal = () => {
    setModalVisible(true);
  };

  const closeModal = () => {
    setModalVisible(false);
  };

  return (
    <div className="home-container">
      <h1 className="home-title">Hello, A-Ko!</h1>
      <img className="home-image" src={Ako_sit} alt="Home" />
      <div className="home-chat">
        <input
          type="text"
          placeholder="안녕, 오랜만이야. 요즘 학교 생활은 어때?"
          className="home-chat-input"
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
        />
        <button className="home-chat-button" onClick={gotoChat}>
          start chat
        </button>
      </div>
      <div className="home-options">
        <div className="home-option">
          <button className="home-option-icon" onClick={gotoCredit}></button>
          <span className="home-option-text" onClick={gotoCredit}>
            ID’s 취득 학점
          </span>
        </div>
        <div className="home-option">
          <button className="home-option-icon" onClick={openModal}></button>
          <span className="home-option-text" onClick={openModal}>
            YY %학기 시간표
          </span>
        </div>
        <div className="home-option">
          <button className="home-option-icon" onClick={gotoChange}></button>
          <span className="home-option-text" onClick={gotoChange}>
            ID's 정보 수정
          </span>
        </div>
      </div>
      {/* 모달 */}
      {modalVisible && (
        <div className="modal-overlay" onClick={closeModal}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <button
              className="modal-close-button"
              onClick={closeModal}
              style={{
                borderRadius: "50%",
                backgroundColor: "#F4E7DA",
                border: "1.5px solid #B7A08E",
                width: "40px",
                height: "40px",
                cursor: "pointer",
              }}
            >
              X
            </button>
            <iframe
              src="/timetable"
              title="TimeTable"
              className="modal-iframe"
            ></iframe>
          </div>
        </div>
      )}
    </div>
  );
};

export default Home;
